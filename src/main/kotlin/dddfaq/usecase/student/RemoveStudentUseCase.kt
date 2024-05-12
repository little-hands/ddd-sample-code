package dddfaq.usecase.student

import dddfaq.domain.club.*
import dddfaq.domain.student.StudentId

class RemoveStudentUseCase(
    private val clubRepository: ClubRepository
) {
    fun execute(studentId: StudentId, clubId: ClubId) {
        val club = clubRepository.findById(clubId)
        club.removerMember(ClubMember(studentId))
        clubRepository.update(club)
    }
}