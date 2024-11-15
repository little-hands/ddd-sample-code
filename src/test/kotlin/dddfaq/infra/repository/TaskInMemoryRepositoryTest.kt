package dddfaq.infra.repository

import dddfaq.domain.shared.EntityNotFoundException
import dddfaq.domain.task.Task
import dddfaq.domain.task.TaskId
import dddfaq.domain.task.TaskName
import dddfaq.domain.task.TaskStatus
import dddfaq.domain.user.UserId
import dddfaq.test.factory.TestTaskFactory
import dddfaq.test.factory.TestUserFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

// @SpringBootTest
private class TaskInMemoryRepositoryTest {
    val taskRepository = TaskInMemoryRepository()

    // insertメソッドのテスト
    @Test
    fun `insert_findById_insertしたものがfindByIdで取得できる`() {
        // given:
        // ユーザーが既に作成されている
        val user = TestUserFactory.create()

        // タスクを作成する
        val createdTask = Task.reconstruct(
            id = TaskId("TaskId"),
            name = TaskName("TaskName"),
            userId = user.id,
            status = TaskStatus.未完了,
            postponeCount = 0,
            dueDate = LocalDate.now()
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

    @Test
    fun `update_値が更新される`() {
        // given:
        // タスクを作成する
        val taskId = TaskId("TaskId")
        val createdTask = Task.reconstruct(
            id = taskId,
            name = TaskName("TaskName"),
            userId = UserId("UserId1"),
            status = TaskStatus.未完了,
            postponeCount = 0,
            dueDate = LocalDate.of(2024, 1, 1)
        )
        taskRepository.insert(createdTask)

        // when:
        // タスクID以外の値が全て異なるインスタンスを作成し、更新する
        val updatedTask = Task.reconstruct(
            id = taskId,
            name = TaskName("UpdatdName"),
            userId = UserId("UserId2"),
            status = TaskStatus.完了,
            postponeCount = 1,
            dueDate = LocalDate.of(2024, 1, 2)
        )
        taskRepository.update(updatedTask)
        val foundTask = taskRepository.findById(taskId)!!

        // then:
        assertEquals(updatedTask.id, foundTask.id)
        assertEquals(updatedTask.name, foundTask.name)
        assertEquals(updatedTask.userId, foundTask.userId)
        assertEquals(updatedTask.status, foundTask.status)
        assertEquals(updatedTask.postponeCount, foundTask.postponeCount)
        assertEquals(updatedTask.dueDate, foundTask.dueDate)
    }

    @Test
    fun `update_ID指定してインスタンスが存在しなかったら例外`() {
        // when:
        val updatedTask = TestTaskFactory.create(
            taskID = TaskId("NotExistsId")
        )
        val executable = { taskRepository.update(updatedTask) }

        // then:
        val exception = assertThrows<EntityNotFoundException>(executable)
        assertEquals("タスクが見つかりません", exception.message)
    }
}