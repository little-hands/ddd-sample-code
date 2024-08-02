package domainevent.domain.shared.other.task

import domainevent.domain.shared.Repository
import org.springframework.context.ApplicationEventPublisher

abstract class TaskRepository2(applicationEventPublisher: ApplicationEventPublisher)
    : Repository<Task2>(applicationEventPublisher) {
    abstract fun fetchById(taskId: TaskId): Task2?
}