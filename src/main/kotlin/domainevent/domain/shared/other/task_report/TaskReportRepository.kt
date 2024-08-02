package domainevent.domain.shared.other.task_report

import domainevent.domain.shared.Repository
import domainevent.domain.shared.other.task.TaskId
import org.springframework.context.ApplicationEventPublisher

abstract class TaskReportRepository(applicationEventPublisher: ApplicationEventPublisher)
    : Repository<TaskReport>(applicationEventPublisher) {
    abstract fun fetchByTaskId(taskId: TaskId): TaskReport?
}