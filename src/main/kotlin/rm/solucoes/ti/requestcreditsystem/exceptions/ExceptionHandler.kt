package rm.solucoes.ti.requestcreditsystem.exceptions

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDate

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream()
                .forEach{ error: ObjectError -> errors[(error as FieldError).field] = error.defaultMessage}

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionDetails(
                title = "Bad Request",
                timestamp = LocalDate.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.name,
                details = errors
        ))
    }

    @ExceptionHandler(DataAccessException::class)
    fun handleValidException(ex: DataAccessException): ResponseEntity<ExceptionDetails> {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionDetails(
                title = "Conflict",
                timestamp = LocalDate.now(),
                status = HttpStatus.CONFLICT.value(),
                exception = ex.javaClass.name,
                details = mutableMapOf(ex.cause.toString().ifEmpty { "error" } to ex.message)
        ))
    }

    @ExceptionHandler(BusinessException::class)
    fun handleValidException(ex: BusinessException): ResponseEntity<ExceptionDetails> {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionDetails(
                title = "Bad Request",
                timestamp = LocalDate.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.name,
                details = mutableMapOf("error" to ex.message)
        ))
    }
}