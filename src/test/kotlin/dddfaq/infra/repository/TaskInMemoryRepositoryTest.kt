package dddfaq.infra.repository

import dddfaq.domain.task.Task
import dddfaq.domain.task.TaskId
import dddfaq.domain.task.TaskName
import dddfaq.domain.task.TaskStatus
import dddfaq.domain.user.User
import dddfaq.domain.user.UserId
import dddfaq.test.factory.TestUserFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

// @SpringBootTest
private class TaskInMemoryRepositoryTest {
    val taskRepository = TaskInMemoryRepository()

    // insertメソッドのテスト
    @Test
    fun `insertしたものがfindByIdで取得できる`() {
        // given:
        // ユーザーが既に作成されている
        val user = TestUserFactory.create()

        val createdTask = Task.create(
            name = TaskName("新しいタスク"),
            dueDate = LocalDate.now(),
            userId = user.id
        )

        // when: インサートし、結果をID指定で取得する⑤
        taskRepository.insert(createdTask)
        val foundTask = taskRepository.findById(createdTask.id)!!

        // then: インサートしたインスタンスが、リポジトリ経由で取得できる⑥
        assertEquals(createdTask.id, foundTask.id)
        assertEquals(createdTask.name, foundTask.name)
        assertEquals(createdTask.userId, foundTask.userId)
        assertEquals(createdTask.status, foundTask.status)
        assertEquals(createdTask.postponeCount, foundTask.postponeCount)
        assertEquals(createdTask.dueDate, foundTask.dueDate)
    }

    @Test
    fun `findById_ヒットしなければnullが返される`() {
        // when:
        val result = taskRepository.findById(TaskId("NotExistsId"))

        // then:
        assertNull(result)
    }

}