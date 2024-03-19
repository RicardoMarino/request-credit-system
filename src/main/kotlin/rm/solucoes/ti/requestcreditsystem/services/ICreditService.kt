package rm.solucoes.ti.requestcreditsystem.services

import rm.solucoes.ti.requestcreditsystem.entities.Credit
import java.util.UUID

interface ICreditService {
    fun save(credit:Credit) : Credit
    fun findAllByCustomer(customerId:Long) : List<Credit>
    fun findByCreditCode(customerId: Long,creditCode:UUID) : Credit
}