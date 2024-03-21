package rm.solucoes.ti.requestcreditsystem.dtos

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import rm.solucoes.ti.requestcreditsystem.entities.Customer
import rm.solucoes.ti.requestcreditsystem.entities.Address
import java.math.BigDecimal

data class CustomerUpdateDTO (
    @field:NotEmpty(message = "First name cannot be empty")
    val firstName: String,
    @field:NotEmpty(message = "Last name cannot be empty")
    val lastName: String,
    @field:Min(value = 0, message = "Income must be greater than zero")
    val income: BigDecimal,
    @field:NotEmpty(message = "Zip code cannot be empty")
    val zipCode: String,
    @field:NotEmpty(message = "Street cannot be empty")
    val street: String
){
    fun parseToEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
        return customer
    }
}