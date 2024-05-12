package dddfaq.domain.task

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

private class TaskTest {
    @Test
    fun `新規作成時にタスクが未完了、延期回数が0であること`() {
        // given:

        // when:
        val task = Task.create(TaskName("タスク名"), LocalDate.now())

        // then:
        // 作成したタスクが未完了であること
        assertEquals(TaskStatus.未完了, task.status)
        assertEquals(0, task.postponeCount)
    }

}