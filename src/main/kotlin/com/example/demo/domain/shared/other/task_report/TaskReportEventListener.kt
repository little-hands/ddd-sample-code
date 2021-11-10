package com.example.demo.domain.shared.other.task_report

import com.example.demo.domain.shared.other.task.TaskCreatedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TaskReportEventListener(private val taskReportRepository: TaskReportRepository) {
    @EventListener
    internal fun createReport(event: TaskCreatedEvent) {
        taskReportRepository.insert(TaskReport(event))
    }
}