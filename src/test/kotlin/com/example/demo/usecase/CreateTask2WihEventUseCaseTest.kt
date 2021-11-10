package com.example.demo.usecase

import com.example.demo.domain.shared.other.CreateTaskWihEventUseCase
import com.example.demo.domain.shared.other.task.TaskId
import com.example.demo.domain.shared.other.task.TaskRepository2
import com.example.demo.domain.shared.other.task_report.TaskReportRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CreateTask2WihEventUseCaseTest(
  @Autowired private val createTaskWihEventUseCase: CreateTaskWihEventUseCase,
  @Autowired private val taskRepository: TaskRepository2,
  @Autowired private val taskReportRepository: TaskReportRepository
) {
    @Test
    fun `タスク名を指定してタスク作成すると、タスクとタスクレポートが保存される`() {
        // when:
        val taskId: TaskId = createTaskWihEventUseCase.execute("新規機能開発")

        // then:
        val fetchTask = taskRepository.fetchById(taskId)!!
        assertEquals("新規機能開発", fetchTask.name)

        // ドメインイベントを経由して、タスクレポートが作成されている
        /**
         * ドメインイベントを投げる処理は
         * [com.example.demo.domain.shared.Repository]
         *
         * ドメインイベントを拾う処理は
         * [com.example.demo.domain.task_report.TaskReportEventListener]
         *
         * を参照してください
         */
        val fetchedReport = taskReportRepository.fetchByTaskId(taskId)!!
        assertEquals("タスクが作成されました。  タスク名: 新規機能開発", fetchedReport.detail)
    }
}