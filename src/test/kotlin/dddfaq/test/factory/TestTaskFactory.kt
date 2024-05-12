package dddfaq.test.factory

import dddfaq.domain.task.Task
import dddfaq.domain.task.TaskId
import dddfaq.domain.task.TaskName
import dddfaq.domain.task.TaskStatus
import dddfaq.domain.user.UserId
import java.time.LocalDate

object TestTaskFactory {
    fun create(
        // ① 引数には全てデフォルト値を設定する
        taskID: TaskId = TaskId(),
        status: TaskStatus = TaskStatus.未完了,
        taskName: String = "task1",
        postponeCount: Int = 0,
        dueDate: LocalDate = LocalDate.now(),
        userId: UserId = UserId()
    ): Task {
        return Task.reconstruct(
            id = taskID,
            name = TaskName(taskName),
            status = status,
            postponeCount = postponeCount,
            dueDate = dueDate,
            userId = userId
        )
    }
}