package dddfaq.usecase.task

import dddfaq.domain.task.*
import java.time.LocalDate

class CreateBadTaskUseCase(val taskRepository: TaskRepository) {
    fun execute(taskName: String, dueDate: LocalDate) {
//        val instans = Task()
//
//        val task = Task(
//            id = TaskId(),
//            name = TaskName(taskName),
//            postponeCount = -100, // x: ま、マイナス！？
//            dueDate = LocalDate.of(2100, 1, 0), // x: いつ！？
//            status = TaskStatus.完了 // x: いきなり完了！？
//        )
//        taskRepository.insert(task)
    }
}