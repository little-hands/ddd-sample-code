package com.example.demo.domain.task_report

import com.example.demo.domain.shared.AggregateRoot
import com.example.demo.domain.task.TaskCreatedEvent
import com.example.demo.domain.task.TaskId
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

