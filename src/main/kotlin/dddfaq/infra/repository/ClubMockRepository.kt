package dddfaq.infra.repository

import dddfaq.domain.club.Club
import dddfaq.domain.club.ClubId
import dddfaq.domain.club.ClubRepository

class ClubMockRepository: ClubRepository {
    override fun insert(club: Club) {
        // Clubインスタンスから、CLUBテーブル、CLUB_MEMBERテーブルのインサート文を作成し、実行する
        // ORMを使用してSQL実行することもあり
        TODO("Not yet implemented")

    }

    override fun findById(clubId: ClubId): Club {
        TODO("Not yet implemented")
    }

    override fun findByName(name: String): List<Club> {
        TODO("Not yet implemented")
    }

    override fun update(club: Club) {
        TODO("Not yet implemented")
    }

}