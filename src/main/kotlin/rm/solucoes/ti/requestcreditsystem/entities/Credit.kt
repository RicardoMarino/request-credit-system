package rm.solucoes.ti.requestcreditsystem.entities

import jakarta.persistence.*
import rm.solucoes.ti.requestcreditsystem.entities.enums.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
data class Credit (
        @Column(nullable = false, unique = true) val creditCode: UUID = UUID.randomUUID(),
        @Column(nullable = false)val creditValue: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false)val dayFirstInstallment: LocalDate,
        @Column(nullable = false)val numberOfInstallments: Int = 0,
        @Enumerated val status: Status = Status.IN_PROGRESS,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
        val customer: Customer? = null,
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) val id: Long? = null
)
