package dddfaq.domain.club

import dddfaq.domain.shared.DomainException
import dddfaq.domain.student.StudentId

class ClubMember(val studentId: StudentId)

class Club(
    val id: ClubId,
    val name: String,
    status: ClubStatus,
    val members: List<ClubMember>
) {
    var status = status
        private set  // setterをprivateにしている

    fun approve(authorizedRole: AuthorizedRole) {
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

data class AuthorizedRole(
    val role :Role
)

enum class Role {
    学生, 教員, 事務職員
}

enum class ClubStatus {
    未承認, 承認
}


data class ClubId(val value: String)

