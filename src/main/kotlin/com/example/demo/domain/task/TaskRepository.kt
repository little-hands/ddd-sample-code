package com.example.demo.domain.task

import com.example.demo.domain.shared.Repository
import org.springframework.context.ApplicationEventPublisher

abstract class TaskRepository(applicationEventPublisher: ApplicationEventPublisher)
    : Repository<Task>(applicationEventPublisher) {
    abstract fun fetchById(taskId: TaskId): Task?
}