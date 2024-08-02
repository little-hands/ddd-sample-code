package domainevent.domain.shared.other

import domainevent.domain.shared.DomainEventSeedFactory
import domainevent.domain.shared.other.task.Task2
import domainevent.domain.shared.other.task.TaskId
import domainevent.domain.shared.other.task.TaskRepository2
import org.springframework.stereotype.Component

@Component
class CreateTaskWihEventUseCase(
    private val taskRepository: TaskRepository2,
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
        val task = Task2("新規機能開発", domainEventSeed)
        taskRepository.insert(task)
        return task.taskId
    }
}