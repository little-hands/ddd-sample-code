package dddfaq.usecase.task

import dddfaq.domain.task.Task
import dddfaq.domain.task.TaskId
import dddfaq.domain.task.TaskName
import dddfaq.domain.task.TaskRepository
import dddfaq.domain.user.UserId
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class CreateTaskUseCase(private val taskRepository: TaskRepository) {
    fun execute(taskName: String, dueDate: LocalDate, userId: UserId): TaskId {
        val task = Task.create(TaskName(taskName), dueDate, userId)
        taskRepository.insert(task)
        return task.id
    }
}