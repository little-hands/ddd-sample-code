package com.example.demo.infra.task

import com.example.demo.domain.task.Task
import com.example.demo.domain.task.TaskId
import com.example.demo.domain.task.TaskRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class TaskMockRepository(applicationEventPublisher: ApplicationEventPublisher) : TaskRepository(applicationEventPublisher) {
    private val map: MutableMap<TaskId, Task> = mutableMapOf()

    override fun fetchById(taskId: TaskId): Task? {
        return map[taskId]
    }

    override fun insertImpl(entity: Task) {
        map[entity.taskId] = entity
    }

}