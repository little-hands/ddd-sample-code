package domainevent.domain.shared.other.task

import domainevent.domain.shared.DomainEventSeed
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Task2Test {
    @Test
    fun `タスク作成`() {
        // when:
        val mockSeed: DomainEventSeed = mockk()
        val task = Task2("新規機能開発", mockSeed)

        // then:
        // 引数で渡した名前でタスクが作成されていること
        assertEquals("新規機能開発", task.name)

        // TaskCreatedEventが作られていること
        task.getDomainEvents().also {
            assertEquals(1, it.size)
            assertTrue(it[0] is TaskCreatedEvent)
        }
    }
}
