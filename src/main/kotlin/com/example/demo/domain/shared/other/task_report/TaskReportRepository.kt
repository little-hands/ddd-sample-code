package com.example.demo.domain.shared.other.task_report

import com.example.demo.domain.shared.Repository
import com.example.demo.domain.shared.other.task.TaskId
import org.springframework.context.ApplicationEventPublisher

abstract class TaskReportRepository(applicationEventPublisher: ApplicationEventPublisher)
    : Repository<TaskReport>(applicationEventPublisher) {
    abstract fun fetchByTaskId(taskId: TaskId): TaskReport?
}