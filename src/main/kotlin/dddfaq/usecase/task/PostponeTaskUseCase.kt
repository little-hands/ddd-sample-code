package dddfaq.usecase.task

import dddfaq.domain.task.Task
import dddfaq.domain.task.TaskId
import dddfaq.domain.task.TaskRepository
import dddfaq.usecase.shared.UseCaseException

class PostponeTaskUseCase(val taskRepository: TaskRepository) {
    fun execute(taskId: TaskId) {
        val task = findTask(taskId)
        task.postpone()
        taskRepository.update(task)
    }

    // private methods ------------------------

    private fun findTask(taskId: TaskId): Task =
        taskRepository.findById(taskId) ?: throw UseCaseException("タスクが見つかりません")
}