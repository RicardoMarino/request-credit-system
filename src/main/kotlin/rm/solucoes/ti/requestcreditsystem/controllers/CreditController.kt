package rm.solucoes.ti.requestcreditsystem.controllers

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rm.solucoes.ti.requestcreditsystem.dtos.CreditDTO
import rm.solucoes.ti.requestcreditsystem.dtos.CreditView
import rm.solucoes.ti.requestcreditsystem.dtos.CreditViewList
import rm.solucoes.ti.requestcreditsystem.entities.Credit
import rm.solucoes.ti.requestcreditsystem.services.ICreditService
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/api/v1/credits")
class CreditController (
    private val creditService: ICreditService
){
    @PostMapping
    fun save(@RequestBody @Valid creditDTO: CreditDTO): ResponseEntity<String> {
        val save = this.creditService.save(creditDTO.parseToEntity())
        return ResponseEntity.created(URI("/api/v1/credits/${save.creditCode}")).body("Credit ${save.creditCode} saved with success")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam("customerId") customerId: Long) : ResponseEntity<List<CreditViewList>> =
            ResponseEntity.ok().body(this.creditService.findAllByCustomer(customerId)
            .map { credit: Credit ->  CreditViewList(credit) }.toList())

    @GetMapping("/by-credit-code")
    fun findByCreditCode(@RequestParam("customerId") customerId: Long,
                         @RequestParam("creditCode") creditCode: UUID ) : ResponseEntity<CreditView> =
            ResponseEntity.ok().body(CreditView(this.creditService.findByCreditCode(customerId, creditCode)))
}