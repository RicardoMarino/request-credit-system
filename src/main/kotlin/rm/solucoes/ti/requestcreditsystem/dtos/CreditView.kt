package rm.solucoes.ti.requestcreditsystem.dtos

import rm.solucoes.ti.requestcreditsystem.entities.Credit
import rm.solucoes.ti.requestcreditsystem.entities.enums.Status
import java.math.BigDecimal
import java.util.*

data class CreditView(
    val creditValue: BigDecimal,
    val creditCode: UUID,
    val numberOfInstallments: Int,
    val status: Status,
    val emailCustomer: String,
    val incomeCustomer: BigDecimal
){

    constructor(credit: Credit): this(
        creditValue = credit.creditValue,
        creditCode = credit.creditCode,
        numberOfInstallments = credit.numberOfInstallments,
        status = credit.status,
        emailCustomer = credit.customer?.email ?: "",
        incomeCustomer = credit.customer?.income ?: BigDecimal.ZERO
    )
}
