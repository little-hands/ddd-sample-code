@file:Suppress("EnumEntryName")

package domainevent.domain.screening

import java.time.LocalDate

/** 採用選考 */
class Screening(
    val id: ScreeningId,
    /** 採用選考ステータス */
  val status: ScreeningStatus,
    /** 応募経路 */
  val applicationRoute: String,
    val applicant: Applicant,
    val positionId: PositionId,
    interviews: List<Interview>
) {
  var interviews: List<Interview> = interviews
    private set

  fun addInterview(interviewDate: LocalDate) {
    if (this.status != ScreeningStatus.選考中) {
      // 選考中でなければ、面接は追加できない → これがドメイン層の実装になった
      throw RuntimeException("選考中ではない採用選考に面接を追加できません") // DomainExceptionなど、ドメイン層に定義する例外を投げたい
    }

    val newInterview = Interview(
      // 2個目以降の面接は、「面接次数」が既存の最大値＋1  → これがドメイン層の実装になった
      interviewNumber = this.interviews.size + 1,
      interviewDate = interviewDate
    )
    // 新しい面接を追加
    this.interviews = this.interviews + newInterview
  }
}

data class ScreeningId(val value: String)

interface ScreeningRepository {
  fun insert(screening: Screening)
  fun findById(screeningId: ScreeningId): Screening?
  fun update(screening: Screening)
}

// ---------------------------------

class AddInterviewUseCase(val screeningRepository: ScreeningRepository) {
  fun execute(screeningId: ScreeningId, interviewDate: LocalDate) {
    val screening = screeningRepository.findById(screeningId)!! // 採用選考を取得する
    screening.addInterview(interviewDate) // 面接を追加する
    screeningRepository.update(screening) // 採用選考を保存する
  }
}

// ---------------------------------

/** 面接 */
class Interview(val interviewNumber: Int, val interviewDate: LocalDate)


/** 採用選考ステータス */
enum class ScreeningStatus {
  選考中, 内定, 内定承諾, 入社, 不合格
}

/** 応募者 */
class Applicant(val name: String, val mailAddress: String)

// --------- 採用ポジション集約 ---------

// package dddfaq.domain.poisiton
/** 採用ポジション */
class Position(
    val id: PositionId,
    val name: String
)

interface PositionRepository {
  fun insert(position: Position)
  fun findById(positionId: PositionId): Position?
}

data class PositionId(val value: String)





