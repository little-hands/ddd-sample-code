package dddfaq.test.factory

import dddfaq.domain.user.User
import dddfaq.domain.user.UserId
import dddfaq.domain.user.UserName

object TestUserFactory {
    fun create(
        id: UserId = UserId(),
        name: String = "name"
    ): User {
        return User(
            id = UserId(),
            name = UserName(name)
        )
    }

}
