package rm.solucoes.ti.requestcreditsystem.services.implement

import org.springframework.stereotype.Service
import rm.solucoes.ti.requestcreditsystem.entities.Credit
import rm.solucoes.ti.requestcreditsystem.exceptions.BusinessException
import rm.solucoes.ti.requestcreditsystem.repositories.ICreditRepository
import rm.solucoes.ti.requestcreditsystem.services.ICreditService
import rm.solucoes.ti.requestcreditsystem.services.ICustomerService
import java.time.LocalDate
import java.util.*
@Service
class CreditService(
    private val creditRepository: ICreditRepository,
    private val customerService: ICustomerService
) : ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        val dateFuture = LocalDate.now().plusMonths(3);
        if(credit.dayFirstInstallment.isAfter(dateFuture))
            throw BusinessException("The first installment must be no more than 3 months")

        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit>  = this.creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit = this.creditRepository.findCreditByCreditCode(creditCode) ?: throw BusinessException("Credit not found")
        return if(credit.customer?.id == customerId) credit else throw BusinessException("Contact Admin")
    }
}