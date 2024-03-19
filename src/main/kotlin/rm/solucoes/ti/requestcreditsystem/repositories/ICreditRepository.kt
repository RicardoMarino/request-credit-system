package rm.solucoes.ti.requestcreditsystem.repositories

import org.springframework.data.jpa.repository.JpaRepository
import rm.solucoes.ti.requestcreditsystem.entities.Credit
interface ICreditRepository : JpaRepository<Credit, Long>