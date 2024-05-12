package dddfaq.usecase.task

import dddfaq.domain.task.Task
import dddfaq.domain.task.TaskName
import dddfaq.domain.task.TaskRepository
import dddfaq.domain.task.TaskStatus
import dddfaq.domain.user.UserId
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

private class CreateTaskUseCaseTest {
    // 依存オブジェクトのモック
    private val taskRepository: TaskRepository = mockk() // ①

    // テスト対象
    private val createTaskUseCase = CreateTaskUseCase(taskRepository) // ②

    @Test
    fun `タスク名等を渡すと、その値を使用して新規作成されたタスクが保存される`() {
        // given: TaskRepositoryのinsertメソッドに渡される値をキャプチャする③
        val taskCapturingSlot: CapturingSlot<Task> = slot()
        every { taskRepository.insert(capture(taskCapturingSlot)) } just Runs

        // when:
        val taskName = "新しいタスク"
        val dueDate = LocalDate.of(2021, 8, 1)
        val userId = UserId("user1")
        createTaskUseCase.execute(taskName, dueDate, userId) // ④

        // then: 新規作成されたタスクがリポジトリに渡されている
        val capturedTask: Task = taskCapturingSlot.captured // ⑤
        // repository.insertに渡された値のアサーション⑥
        // ユースケースの引数からタスクが生成されている
        assertEquals(TaskName(taskName), capturedTask.name)
        assertEquals(dueDate, capturedTask.dueDate)
        assertEquals(userId, capturedTask.userId)

        // 新規作成状態のタスクである
        assertEquals(TaskStatus.未完了, capturedTask.status)
    }
}