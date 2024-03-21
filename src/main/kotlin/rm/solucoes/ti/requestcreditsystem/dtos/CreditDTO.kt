package rm.solucoes.ti.requestcreditsystem.dtos

import jakarta.validation.constraints.*
import rm.solucoes.ti.requestcreditsystem.entities.Credit
import rm.solucoes.ti.requestcreditsystem.entities.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDTO(
    @field:Min(value = 0, message = "Credit value must be greater than 0")
    val creditValue: BigDecimal,
    @field:Min(value = 1, message = "Number of installments must be greater than 1")
    @field:Max(value = 48, message = "Number of installments must be less than 48")
    val numberOfInstallments: Int,
    @field:Future(message = "First installment must be in the future")
    val dayFirstInstallment: LocalDate,
    @field:NotNull(message = "Customer id cannot be null")
    val customerId : Long
) {
    fun parseToEntity(): Credit {
        return Credit(
            creditValue = this.creditValue,
            numberOfInstallments = this.numberOfInstallments,
            dayFirstInstallment = this.dayFirstInstallment,
            customer = Customer(id = this.customerId)
        )
    }
}
