package dddfaq.domain.user

import java.util.*

class User(
    val id: UserId,
    val name: UserName
)

data class UserName(val value: String)

data class UserId(val value: String = UUID.randomUUID().toString())