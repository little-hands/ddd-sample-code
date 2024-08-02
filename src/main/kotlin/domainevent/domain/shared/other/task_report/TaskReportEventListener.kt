package domainevent.domain.shared.other.task_report

import domainevent.domain.shared.other.task.TaskCreatedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TaskReportEventListener(private val taskReportRepository: TaskReportRepository) {
    @EventListener
    internal fun createReport(event: TaskCreatedEvent) {
        taskReportRepository.insert(TaskReport(event))
    }
}