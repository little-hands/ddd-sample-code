package dddfaq.domain.club

import org.junit.jupiter.api.Test

private class ClubTest {
    @Test
    fun `新規作成時にxxxであること`() {
        // given:
        Club(
            id = ClubId("1"),
            name = "テニス部",
            status = ClubStatus.未承認,
            members = emptyList()
        )

        // when:

        // then:
    }
}