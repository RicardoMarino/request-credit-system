package rm.solucoes.ti.requestcreditsystem.exceptions

import java.time.LocalDate

data class BusinessException(override val message: String?) : RuntimeException(message){}