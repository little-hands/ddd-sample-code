package dddfaq.domain.user

import java.util.*

data class UserId(val value: String = UUID.randomUUID().toString())