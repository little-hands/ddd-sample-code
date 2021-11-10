package dddfaq.domain.task

import dddfaq.domain.shared.DomainException
import dddfaq.domain.shared.ULID
import java.time.LocalDate


class Task private constructor(
  id: TaskId, name: TaskName, postponeCount: Int, dueDate: LocalDate
) {
  // 不変な属性
  val id = id
  val name = name

  // 可変な属性
  var postponeCount = postponeCount
    private set
  var dueDate = dueDate
    private set

  // ユーザーID、ステータスは省略

  companion object {

    /** 新しいタスクを作成します */
    fun create(name: TaskName, dueDate: LocalDate): Task { // ③
      return Task(
        id = TaskId(),
        name = name,
        postponeCount = 0,
        dueDate = dueDate
      )
    }

    fun reconstruct(
      id: TaskId,
      name: TaskName,
      postponeCount: Int,
      dueDate: LocalDate
    ): Task {
      return Task(
        // ①
        id = id,
        name = name,
        postponeCount = postponeCount,
        dueDate = dueDate,
      )
    }

    private const val MAX_POSTPONE_COUNT = 3
  }

  /** タスクの期日を延期します */
  fun postpone() {
    if (this.postponeCount >= MAX_POSTPONE_COUNT)
      throw DomainException("最大延期回数を超えています")
    this.dueDate = dueDate.plusDays(1)
    this.postponeCount += 1
  }
}

data class TaskId(val value: String = ULID.random())

data class TaskName(val value: String) {
  init {
    if (value.length > TASK_NAME_MAX_LENGTH) {
      throw DomainException("タスク名は10文字以下で入力して下さい")
    }
  }

  companion object {
    private const val TASK_NAME_MAX_LENGTH = 10
  }
}

