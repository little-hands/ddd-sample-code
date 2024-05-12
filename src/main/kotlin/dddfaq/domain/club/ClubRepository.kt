package dddfaq.domain.club


interface ClubRepository {
    fun insert(club: Club)
    fun findById(clubId: ClubId): Club
    fun findByName(name: String): List<Club>
    fun update(club: Club)

//    fun addMember(clubId: ClubId, clubMember: ClubMember) // x: リポジトリの責務を超えている
//    fun approve(clubId: ClubId) // x: リポジトリの責務を超えている
}