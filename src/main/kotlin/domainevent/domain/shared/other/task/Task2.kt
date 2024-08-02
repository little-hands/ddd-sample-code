package domainevent.domain.shared.other.task

import domainevent.domain.shared.AggregateRoot
import domainevent.domain.shared.DomainEvent
import domainevent.domain.shared.DomainEventSeed
import java.util.*

/**
 * タスクを表すクラス
 */
class Task2 private constructor(val taskId: TaskId, val name: String) : AggregateRoot() {
    constructor(name: String, seed: DomainEventSeed) : this(taskId = TaskId(), name = name) {
        super.addDomainEvent(TaskCreatedEvent(taskId, name, seed))
    }
}

class TaskId(val value: String) {
    constructor() : this(UUID.randomUUID().toString())
}

/**
 * タスクが作成されたことを示すイベントです
 */
class TaskCreatedEvent(val taskId: TaskId, val taskName: String, seed: DomainEventSeed) : DomainEvent(seed)