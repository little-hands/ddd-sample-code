package dddfaq.task

import dddfaq.domain.task.TaskId
import dddfaq.domain.task.TaskRepository

class PostponeTaskUseCase(val taskRepository: TaskRepository) {
    fun execute(taskId: TaskId) {
        val task = taskRepository.findById(taskId)
        task.postpone()
        taskRepository.update(task)
    }
}