package dddfaq.example.domain.task

import dddfaq.domain.shared.DomainException
import dddfaq.domain.task.Task
import dddfaq.domain.task.TaskId
import dddfaq.domain.task.TaskName
import dddfaq.domain.user.UserId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

private class TaskTestExample {

  @Nested
  inner class CreateTest {
    @Test
    fun `新しくタスクを作成すると、未完了で延期回数0のインスタンスが生成される`() {
      // given(前提条件): ②

      // when(操作): ③
      val taskName = TaskName("新しいタスク")
      val dueDate = LocalDate.of(2021, 12, 1)
      val task = Task.create(taskName, dueDate)

      // then(期待する結果): ④
      // ステータス未完了、延期回数0で生成される ⑤
      assertEquals(0, task.postponeCount)

      // 以下の属性は引数の値がそのまま設定される
      assertEquals(taskName, task.name)
      assertEquals(dueDate, task.dueDate)
    }

    @Test
    fun `新しくタスクを作成すると、未完了で延期回数0のインスタンスが生成される2`() {
      // given(前提条件): ②

      // when(操作): ③
      val taskName = TaskName("新しいタスク")
      val dueDate = LocalDate.of(2021, 12, 1)
      val userId = UserId("user1")
      val task = Task.create(taskName, dueDate)

      // then(期待する結果): ④
      assertEquals(0, task.postponeCount, "延期回数の初期値は0で生成される")

      // 以下の属性は引数の値がそのまま設定される
      assertEquals(taskName, task.name, "引数の値がそのまま設定される")
      assertEquals(dueDate, task.dueDate, "引数の値がそのまま設定される")
    }
  }

  @Nested
  inner class ReconstructTest {
    @Test
    fun `reconstructに値を渡すと、渡した値でインスタンスが作成される`() {
      // given:
      val taskId = TaskId("task1")
      val name = TaskName("タスク名")
      val userId = UserId("user1")
      val postponeCount = 2
      val dueDate = LocalDate.of(2021, 8, 1)

      // when:
      val task = Task.reconstruct(
        id = taskId,
        name = name,
        postponeCount = postponeCount,
        dueDate = dueDate
      )

      // then:
      assertEquals(taskId, task.id)
      assertEquals(name, task.name)
      assertEquals(postponeCount, task.postponeCount)
      assertEquals(dueDate, task.dueDate)
    }

  }

  @Nested
  inner class PostponeTest {

    @Test
    fun `タスクを延期すると、期日が1日後になり延期回数が1回増える`() { // ①
      // given:
      val dueDate = LocalDate.of(2021, 8, 1)
      val task = Task.create(TaskName("タスク"), dueDate) // ②

      // when:
      task.postpone() // ③

      // then: ④
      assertEquals(LocalDate.of(2021, 8, 2), task.dueDate)
      assertEquals(1, task.postponeCount)
    }

    @Test
    fun `最大回数延期されている場合、再度延期すると例外が発生する`() {
      // given: タスクが既に3回延期されている ①
      Task.reconstruct( // 再構成メソッドを使って全ての属性に任意の値を指定
        id = TaskId(),
        name = TaskName("taskName"),
        postponeCount = 3,
        dueDate = LocalDate.now()
      )
      val task = Task.create(TaskName("タスク"), LocalDate.now())
      task.postpone()
      task.postpone()
      task.postpone()

      // when:
      val target: () -> Unit = { // ②
        task.postpone()
      }

      // then:
      val exception = assertThrows<DomainException>(target) // ③
      assertEquals("最大延期回数を超えています", exception.message)
    }
  }

}