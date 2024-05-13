package dddfaq.usecase.task

import dddfaq.domain.task.Task
import dddfaq.domain.task.TaskId
import dddfaq.domain.task.TaskRepository
import dddfaq.domain.task.TaskStatus
import dddfaq.domain.user.User
import dddfaq.domain.user.UserId
import dddfaq.domain.user.UserRepository
import dddfaq.usecase.shared.UseCaseException
import org.springframework.stereotype.Component
import java.lang.IllegalStateException

@Component
class FetchTaskDetailUseCase {
    private val taskRepository: TaskRepository
    private val userRepository: UserRepository

    constructor(taskRepository: TaskRepository, userRepository: UserRepository) {
        this.taskRepository = taskRepository
        this.userRepository = userRepository
    }

    fun execute(taskId: TaskId): FetchTaskDetailDto? {
        val task = taskRepository.findById(taskId) ?: return null // ①
        val user = findUser(task.userId) // ②
        return FetchTaskDetailDto(task, user) // ③
    }

    // private methods ------------------------
    private fun findUser(userId: UserId): User {
        return userRepository.findById(userId)
            ?: throw IllegalStateException("ユーザーが見つかりません")
    }
}

data class FetchTaskDetailDto(
    val taskId: String,
    val name: String,
    val status: TaskStatus,
    val postponeCount: Int,
    val userId: String,
    val userName: String
) {
    constructor(task: Task, user: User) : this(
        taskId = task.id.value, name = task.name.value, status = task.status,
        postponeCount = task.postponeCount,
        userId = task.userId.value,
        userName = user.name.value
    )
}