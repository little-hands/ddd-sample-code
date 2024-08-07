package dddfaq.usecase.task

import dddfaq.domain.task.TaskId
import dddfaq.domain.task.TaskRepository
import dddfaq.test.factory.TestUserFactory
import dddfaq.domain.user.UserRepository
import dddfaq.test.factory.TestTaskFactory
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

private class FetchTaskDetailUseCaseTest {
    // 依存オブジェクトのモック①
    private val taskRepository: TaskRepository = mockk()
    private val userRepository: UserRepository = mockk()

    // テスト対象
    private val useCase = FetchTaskDetailUseCase(taskRepository, userRepository)

    @Test
    fun `2つのリポジトリから値が取得できる場合、結果がDTOに詰め替えて返される`() {
        // given: ユーザーリポジトリ、タスクリポジトリが値を返す
        val user = TestUserFactory.create(name="user1") // ②
        every { userRepository.findById(user.id) }.returns(user) // ③
        val task = TestTaskFactory.create(userId = user.id) // ④
        every { taskRepository.findById(task.id) }.returns(task) // ⑤

        // when:
        val actualDto = useCase.execute(task.id) // ⑥

        // then:
        val expectDto = FetchTaskDetailDto(
            taskId = task.id.value,
            name = task.name.value,
            status = task.status,
            postponeCount = task.postponeCount,
            userId = task.userId.value,
            userName = user.name.value
        )
        assertEquals(expectDto, actualDto) // ⑦
    }

    @Test
    fun `タスクリポジトリから返される値がnullの場合、nullが返される`() { // ①
        // given:
        val taskId = TaskId()
        every { taskRepository.findById(taskId) }.returns(null) // ②

        // when:
        val actualDto = useCase.execute(taskId)

        // then:
        assertNull(actualDto) // ③
    }
}