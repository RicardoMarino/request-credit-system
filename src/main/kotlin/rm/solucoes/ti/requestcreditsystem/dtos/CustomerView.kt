package rm.solucoes.ti.requestcreditsystem.dtos
import rm.solucoes.ti.requestcreditsystem.entities.Customer

import java.math.BigDecimal
data class CustomerView(
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val email: String,
    val income: BigDecimal,
    val zipCode: String,
    val street: String
){
    constructor(customer: Customer) : this (
        firstName = customer.firstName,
        lastName = customer.lastName,
        cpf = customer.cpf,
        email = customer.email,
        income = customer.income,
        zipCode = customer.address.zipCode,
        street = customer.address.street
    )
}