package rm.solucoes.ti.requestcreditsystem.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import rm.solucoes.ti.requestcreditsystem.entities.Address
import rm.solucoes.ti.requestcreditsystem.entities.Customer
import java.math.BigDecimal

data class CustomerDTO(
    @field:NotEmpty(message = "First name cannot be empty")
    val firstName: String,
    @field:NotEmpty(message = "Last name cannot be empty")
    val lastName: String,
    @field:NotEmpty(message = "Cpf cannot be empty")
    @field:CPF(message = "Cpf invalid")
    val cpf: String,
    @field:NotNull(message = "Income cannot be null")
    @field:Min(value = 0, message = "Income cannot be negative")
    val income: BigDecimal,
    @field:NotEmpty(message = "Email cannot be empty")
    @field:Email(message = "Email invalid")
    val email: String,
    @field:NotEmpty(message = "Email cannot be empty")
    @field:Min(value = 6, message = "Password must have at least 6 characters")
    val password: String,
    @field:NotEmpty(message = "Zip code cannot be empty")
    val zipCode: String,
    @field:NotEmpty(message = "Street cannot be empty")
    val street: String
){
    fun parseToEntity(): Customer {
        return Customer(
            firstName = this.firstName,
            lastName = this.lastName,
            cpf = this.cpf,
            income = this.income,
            email = this.email,
            password = this.password,
            address = Address(
                zipCode = this.zipCode,
                street = this.street
            )
        )
    }
}
