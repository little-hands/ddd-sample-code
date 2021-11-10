package com.example.demo.domain.shared.other.task

import com.example.demo.domain.shared.Repository
import org.springframework.context.ApplicationEventPublisher

abstract class TaskRepository2(applicationEventPublisher: ApplicationEventPublisher)
    : Repository<Task2>(applicationEventPublisher) {
    abstract fun fetchById(taskId: TaskId): Task2?
}