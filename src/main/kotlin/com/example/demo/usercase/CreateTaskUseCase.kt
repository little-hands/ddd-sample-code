package com.example.demo.usercase

import com.example.demo.domain.shared.DomainEventSeedFactory
import com.example.demo.domain.task.Task
import com.example.demo.domain.task.TaskId
import com.example.demo.domain.task.TaskRepository
import org.springframework.stereotype.Component

@Component
class CreateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val domainEventSeedFactory: DomainEventSeedFactory
) {
    fun execute(taskName: String): TaskId {
        /*
         * 注: ここで　
         * val task = Task("新規機能開発")
         * とDomainEventSeedを渡さずに生成できてしまうと、
         * なかでイベントが生成されていることに気づけない
         * → まさかTaskReportが生成されるとは夢にも思えないので、
         * せめて「イベントが発行されるから他集約に影響がある可能性があるよー」というのは
         * seedを渡すことでわかるようにしています。
         */
        val domainEventSeed = domainEventSeedFactory.createSeed()
        val task = Task("新規機能開発", domainEventSeed)
        taskRepository.insert(task)
        return task.taskId
    }
}