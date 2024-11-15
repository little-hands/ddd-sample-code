package dddfaq.domain.task

import dddfaq.domain.shared.DomainException
import dddfaq.domain.user.UserId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

private class TaskTest {
    @Nested
    inner class CreateTest {
        @Test
        fun `新しくタスクを作成すると、未完了で延期回数0のインスタンスが生成される`() { // ①
            // given(前提条件): ②
            // when(操作): ③
            val taskName = TaskName("新しいタスク")
            val dueDate = LocalDate.of(2021, 12, 1)
            val userId = UserId("user1")
            val task = Task.create(taskName, dueDate, userId)

            // then(期待する結果): ④
            assertEquals(TaskStatus.未完了, task.status)
            assertEquals(0, task.postponeCount, "延期回数の初期値は0で生成される") // ⑤

            // 以下の属性は引数の値がそのまま設定される⑥
            assertEquals(taskName, task.name)
            assertEquals(dueDate, task.dueDate)
            assertEquals(userId, task.userId)
        }
    }

    @Nested
    inner class PostponeTest {

        @Test
        fun `タスクを延期すると、期日が1日後になり延期回数が1回増える`() { // ①
            // given:
            val dueDate = LocalDate.of(2021, 8, 1)
            val task = Task.create(TaskName("タスク"), dueDate, UserId("user1")) // ②

            // when:
            val postponedTask = task.postpone() // ③

            // then: ④
            assertEquals(LocalDate.of(2021, 8, 2), postponedTask.dueDate)
            assertEquals(1, postponedTask.postponeCount)
        }

        @Test
        fun `最大回数延期されている場合、再度延期すると例外が発生する`() {
            // given: タスクが既に3回延期されている①
            val task = Task.create(TaskName("タスク"), LocalDate.now(), UserId("user1"))
            val task1 = task.postpone()
            val task2 = task1.postpone()
            val task3 = task2.postpone()

            // when:
            val target: () -> Unit = { // ②
                task3.postpone()
            }

            // then:
            val exception = assertThrows<DomainException>(target) // ③
            assertEquals("最大延期回数を超えています", exception.message) // ④
        }
    }

    // -------------------------------------------------------


    
}