package com.example.flowdolist.feature_task.domain.use_case

import com.example.flowdolist.feature_task.data.repository.FakeTaskRepository
import com.example.flowdolist.feature_task.data.repository.TaskRepositoryImpl
import com.example.flowdolist.feature_task.domain.model.Task
import com.example.flowdolist.feature_task.domain.util.OrderType
import com.example.flowdolist.feature_task.domain.util.TaskOrder
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTasksTest {

    private lateinit var getTasks: GetTasks
    private lateinit var fakeRepository: FakeTaskRepository

    @Before
    fun setUp() {
        fakeRepository = FakeTaskRepository()
        getTasks = GetTasks(fakeRepository)

        val tasksToInsert = mutableListOf<Task>()

        ('a'..'z').forEachIndexed { index, c ->
            tasksToInsert.add(
                Task(
                    taskName = c.toString(),
                    taskDescription = c.toString(),
                    taskCreatedAt = index.toLong(),
                    color = index
                )
            )
        }
        tasksToInsert.shuffle()
        runBlocking {
            tasksToInsert.forEach { fakeRepository.upsertTask(it)}
        }
    }

    @Test
    fun `Order tasks by title ascending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Title(OrderType.Ascending)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].taskName).isLessThan(tasks[i+1].taskName)
        }
    }

    @Test
    fun `Order tasks by title descending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Title(OrderType.Descending)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].taskName).isGreaterThan(tasks[i+1].taskName)
        }
    }

    @Test
    fun `Order tasks by date ascending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Date(OrderType.Ascending)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].taskCreatedAt).isLessThan(tasks[i+1].taskCreatedAt)
        }
    }

    @Test
    fun `Order tasks by date descending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Date(OrderType.Descending)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].taskCreatedAt).isGreaterThan(tasks[i+1].taskCreatedAt)
        }
    }

    @Test
    fun `Order tasks by color ascending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Color(OrderType.Ascending)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].color).isLessThan(tasks[i+1].color)
        }
    }

    @Test
    fun `Order tasks by color descending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Color(OrderType.Descending)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].color).isGreaterThan(tasks[i+1].color)
        }
    }
}