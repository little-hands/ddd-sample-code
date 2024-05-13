package dddfaq.infra.repository

import dddfaq.domain.task.*
import dddfaq.domain.user.UserId
import java.time.LocalDate

class TaskInMemoryRepository : TaskRepository {
    private val map = HashMap<TaskId, TaskDbData>()

    override fun insert(task: Task) {
        map[task.id] = TaskMapper.fromEntity(task)
    }

    override fun update(task: Task) {
        TODO("Not yet implemented")
    }

    override fun findById(taskId: TaskId): Task? {
        val nullableTaskDbData = map[taskId]
        if (nullableTaskDbData == null) {
            return nullableTaskDbData
        } else {
            return TaskMapper.toEntity(nullableTaskDbData)
        }
    }
}

/**
 * DBのレコードを模した型
 */
data class TaskDbData(
    val id: String,
    val name: String,
    val dueDate: LocalDate,
    val userId: String,
    val status: String,
    val postponeCount: Int,
)

/**
 * エンティティとDBのレコードをマッピングするクラス
 */
private object TaskMapper {
    fun fromEntity(task: Task): TaskDbData {
        return TaskDbData(
            id = task.id.value,
            name = task.name.value,
            dueDate = task.dueDate,
            userId = task.userId.value,
            status = task.status.name,
            postponeCount = task.postponeCount,
        )
    }

    fun toEntity(data: TaskDbData): Task {
        return Task.reconstruct(
            id = TaskId(data.id),
            name = TaskName(data.name),
            dueDate = data.dueDate,
            userId = UserId(data.userId),
            status = TaskStatus.valueOf(data.status),
            postponeCount = data.postponeCount,
        )
    }
}