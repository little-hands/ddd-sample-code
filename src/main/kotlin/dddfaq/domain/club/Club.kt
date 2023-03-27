package dddfaq.domain.club

import dddfaq.domain.shared.DomainException

class Club(
    val id: ClubId,
    val name: String,
    status: ClubStatus,
    val members: List<ClubMember>
) {
    var status = status
        private set  // setterをprivateにしている

    fun approve() {
        if (members.size < 5) {
            throw DomainException("部員が4人以下なので承認できません")
        }
        this.status = ClubStatus.承認
    }

    fun addMember(clubMember: ClubMember) {
        // studentIdの重複チェックして、membersに1つ追加する
    }

    fun removerMember(clubMember: ClubMember) {
        // 引数のmemberを削除

        // 件数チェック
        if (members.size < 5) {
            this.status = ClubStatus.未承認
        }
    }
}

enum class ClubStatus {
    未承認, 承認
}

class ClubMember(val studentId: StudentId)

data class ClubId(val value: String)

interface ClubRepository {
    fun insert(club: Club)
    fun findById(clubId: ClubId): Club

//    fun addMember(clubId: ClubId, clubMember: ClubMember) // x: リポジトリの責務を超えている
//    fun approve(clubId: ClubId) // x: リポジトリの責務を超えている
}