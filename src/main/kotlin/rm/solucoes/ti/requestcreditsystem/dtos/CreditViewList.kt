package rm.solucoes.ti.requestcreditsystem.dtos

import rm.solucoes.ti.requestcreditsystem.entities.Credit
import java.math.BigDecimal
import java.util.*

data class CreditViewList (
    val creditValue: BigDecimal,
    val creditCode: UUID,
    val numberOfInstallments: Int

){

    constructor(credit: Credit): this(
        creditValue = credit.creditValue,
        creditCode = credit.creditCode,
        numberOfInstallments = credit.numberOfInstallments
    )
}
