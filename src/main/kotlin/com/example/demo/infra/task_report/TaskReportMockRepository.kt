package com.example.demo.infra.task_report

import com.example.demo.domain.task.TaskId
import com.example.demo.domain.task_report.TaskReport
import com.example.demo.domain.task_report.TaskReportId
import com.example.demo.domain.task_report.TaskReportRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class TaskReportMockRepository(applicationEventPublisher: ApplicationEventPublisher) : TaskReportRepository(applicationEventPublisher) {
    private val map: MutableMap<TaskId, TaskReport> = mutableMapOf()

    override fun fetchByTaskId(taskId: TaskId): TaskReport? {
        return map[taskId]
    }

    override fun insertImpl(entity: TaskReport) {
        map[entity.taskId] = entity
    }

}