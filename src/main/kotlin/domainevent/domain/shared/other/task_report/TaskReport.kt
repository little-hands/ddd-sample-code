package domainevent.domain.shared.other.task_report

import domainevent.domain.shared.AggregateRoot
import domainevent.domain.shared.other.task.TaskCreatedEvent
import domainevent.domain.shared.other.task.TaskId
import java.util.*

/**
 * タスクに関するレポートを表すクラス
 */
class TaskReport private constructor(val taskReportId: TaskReportId, val taskId: TaskId, val detail: String) : AggregateRoot() {
    constructor(event: TaskCreatedEvent) : this(
        taskReportId = TaskReportId(),
        taskId = event.taskId,
        detail = "タスクが作成されました。  タスク名: ${event.taskName}"
    )
}


class TaskReportId(val value: String) {
    constructor() : this(UUID.randomUUID().toString())
}

