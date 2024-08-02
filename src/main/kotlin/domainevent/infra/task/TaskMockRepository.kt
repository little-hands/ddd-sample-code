package domainevent.infra.task

import domainevent.domain.shared.other.task.Task2
import domainevent.domain.shared.other.task.TaskId
import domainevent.domain.shared.other.task.TaskRepository2
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class TaskMockRepository(applicationEventPublisher: ApplicationEventPublisher) : TaskRepository2(applicationEventPublisher) {
    private val map: MutableMap<TaskId, Task2> = mutableMapOf()

    override fun fetchById(taskId: TaskId): Task2? {
        return map[taskId]
    }

    override fun insertImpl(entity: Task2) {
        map[entity.taskId] = entity
    }

}