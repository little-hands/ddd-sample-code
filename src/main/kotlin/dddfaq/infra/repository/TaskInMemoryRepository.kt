package dddfaq.infra.repository

import dddfaq.domain.shared.EntityNotFoundException
import dddfaq.domain.task.*
import dddfaq.domain.user.UserId
import java.time.LocalDate

class TaskInMemoryRepository : TaskRepository {
    private val map = HashMap<TaskId, TaskDbData>()

    override fun insert(task: Task) {
        // 実際はinsert文を実行する
        map[task.id] = TaskMapper.fromEntity(task)
    }

    override fun update(task: Task) {
        // 実際のにupdate時の対象チェックは、
        // SQLでselectするか、update文で実行結果が0件だったら例外を投げるなどの対応をする
        if (map[task.id] == null) {
            throw EntityNotFoundException("タスクが見つかりません")
        }

        // 実際はupdate文を実行する
        map[task.id] = TaskMapper.fromEntity(task)
    }

    override fun findById(taskId: TaskId): Task? {
        // 実際はselect文を実行する
        val nullableTaskDbData = map[taskId]

        // 取得できた値の詰め替え
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