package dddfaq.usecase.task

import dddfaq.domain.task.Task
import dddfaq.domain.task.TaskName
import dddfaq.domain.task.TaskRepository
import dddfaq.domain.user.UserId
import java.time.LocalDate


class CreateTaskUseCase(val taskRepository: TaskRepository) {
    fun execute(taskName: String, dueDate: LocalDate, userId: UserId) {
        val task = Task.create(
            name = TaskName(taskName),
            dueDate = dueDate,
            userId = userId
        )
        taskRepository.insert(task)
    }
}