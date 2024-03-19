package rm.solucoes.ti.requestcreditsystem.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import rm.solucoes.ti.requestcreditsystem.entities.Credit
import java.util.*

interface ICreditRepository : JpaRepository<Credit, Long>{
    fun findCreditByCreditCode(creditCode: UUID) : Credit?
    @Query("SELECT c FROM Credit c WHERE c.customer.id = :customerId")
    fun findAllByCustomerId(customerId: Long): List<Credit>
}