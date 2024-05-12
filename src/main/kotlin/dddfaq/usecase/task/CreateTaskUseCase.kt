package dddfaq.usecase.task

import dddfaq.domain.task.Task
import dddfaq.domain.task.TaskName
import dddfaq.domain.task.TaskRepository
import java.time.LocalDate


class CreateTaskUseCase(val taskRepository: TaskRepository) {
    fun execute(taskName: String, dueDate: LocalDate) {
        val task = Task.create(
            name = TaskName(taskName),
            dueDate = dueDate,
        )
        taskRepository.insert(task)
    }
}