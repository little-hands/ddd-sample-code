package dddfaq.domain.task

import dddfaq.domain.shared.DomainException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

private class TaskNameTest {
    @Test
    fun `10文字以下の値を渡すと、引数の値を保持したインスタンスが生成される`() {
        // when:
        val taskName = TaskName("1234567890")

        // then:
        assertEquals("1234567890", taskName.value)
    }

    @Test
    fun `11文字以上の値を渡すと、例外が発生する`() {
        // when:
        val target: () -> Unit = { TaskName("12345678901") }

        // then:
        val exception = assertThrows<DomainException>(target)
        assertEquals("タスク名は10文字以下で入力して下さい", exception.message)
    }
}