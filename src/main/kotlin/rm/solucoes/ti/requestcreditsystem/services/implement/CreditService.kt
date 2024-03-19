package rm.solucoes.ti.requestcreditsystem.services.implement

import rm.solucoes.ti.requestcreditsystem.entities.Credit
import rm.solucoes.ti.requestcreditsystem.repositories.ICreditRepository
import rm.solucoes.ti.requestcreditsystem.services.ICreditService
import rm.solucoes.ti.requestcreditsystem.services.ICustomerService
import java.util.*

class CreditService(
    private val creditRepository: ICreditRepository,
    private val customerService: ICustomerService
) : ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit>  = this.creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit = this.creditRepository.findCreditByCreditCode(creditCode) ?: throw RuntimeException("Credit not found")
        return if(credit.customer?.id == customerId) credit else throw RuntimeException("Contact Admin")
    }
}