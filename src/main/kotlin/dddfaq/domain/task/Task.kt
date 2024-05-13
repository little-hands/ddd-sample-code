package dddfaq.domain.task

import dddfaq.domain.shared.DomainException
import dddfaq.domain.shared.ULID
import dddfaq.domain.user.UserId
import java.time.LocalDate

enum class TaskStatus {
    未完了, 完了
}

class Task private constructor( // ①
    id: TaskId, name: TaskName, userId: UserId,
    status: TaskStatus, postponeCount: Int, dueDate: LocalDate
) {
    // 不変な属性
    val id = id
    val name = name
    val userId = userId

    // ② 可変な属性
    var status = status
        private set
    var postponeCount = postponeCount
        private set
    var dueDate = dueDate
        private set

    companion object {

        private const val MAX_POSTPONE_COUNT = 3

        /** 新しいタスクを作成します*/
        fun create(name: TaskName, dueDate: LocalDate, userId: UserId): Task { //③
            // ここでタスク名の10文字チェックをする
            return Task(
                id = TaskId(),
                name = name,
                userId = userId,
                status = TaskStatus.未完了,
                postponeCount = 0, dueDate = dueDate
            )
        }

        fun reconstruct(
            id: TaskId,
            name: TaskName,
            postponeCount: Int,
            dueDate: LocalDate,
            status: TaskStatus,
            userId: UserId
        ): Task {
            return Task(
                // ①
                id = id,
                name = name,
                postponeCount = postponeCount,
                dueDate = dueDate,
                status = status,
                userId = userId
            )
        }
    }

    /** タスクの期日を延期します */
    fun postpone() {
        if (this.postponeCount >= MAX_POSTPONE_COUNT)
            throw DomainException("最大延期回数を超えています")
        this.dueDate = dueDate.plusDays(1)
        this.postponeCount += 1
    }
}

data class TaskId(val value: String = ULID.random())

data class TaskName(val value: String) {
    init {
        if (value.length > TASK_NAME_MAX_LENGTH) {
            throw DomainException("タスク名は10文字以下で入力して下さい")
        }
    }

    companion object {
        private const val TASK_NAME_MAX_LENGTH = 10
    }
}

