package com.example.flowdolist.feature_task.domain.use_case

import com.example.flowdolist.feature_task.data.repository.FakeTaskRepository
import com.example.flowdolist.feature_task.data.repository.TaskRepositoryImpl
import com.example.flowdolist.feature_task.domain.model.Task
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
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
    fun `Order tasks by title ascending, correct order`() {

    }
}

//we have made a fake repository do the rest some other day.