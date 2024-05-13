package dddfaq.domain.user

interface UserRepository {
    fun findById(userId: UserId): User?
    fun insert(user: User)
}