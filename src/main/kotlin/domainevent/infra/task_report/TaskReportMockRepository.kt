package domainevent.infra.task_report

import domainevent.domain.shared.other.task.TaskId
import domainevent.domain.shared.other.task_report.TaskReport
import domainevent.domain.shared.other.task_report.TaskReportRepository
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