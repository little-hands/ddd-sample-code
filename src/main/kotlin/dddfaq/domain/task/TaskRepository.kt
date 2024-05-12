package dddfaq.domain.task

interface TaskRepository {
    fun insert(task: Task)
    fun update(task: Task)
    fun findById(taskId: TaskId): Task
}