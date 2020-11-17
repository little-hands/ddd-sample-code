package com.example.demo.usercase

import com.example.demo.domain.task.TaskId
import com.example.demo.domain.task.TaskRepository
import com.example.demo.domain.task_report.TaskReportRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CreateTaskUseCaseTest(
    @Autowired private val createTaskUseCase: CreateTaskUseCase,
    @Autowired private val taskRepository: TaskRepository,
    @Autowired private val taskReportRepository: TaskReportRepository
) {
    @Test
    fun `タスク名を指定してタスク作成すると、タスクとタスクレポートが保存される`() {
        // when:
        val taskId: TaskId = createTaskUseCase.execute("新規機能開発")

        // then:
        val fetchTask = taskRepository.fetchById(taskId)!!
        assertEquals("新規機能開発", fetchTask.name)

        val fetchedReport = taskReportRepository.fetchByTaskId(taskId)!!
        assertEquals("タスクが作成されました。  タスク名: 新規機能開発", fetchedReport.detail)
    }
}