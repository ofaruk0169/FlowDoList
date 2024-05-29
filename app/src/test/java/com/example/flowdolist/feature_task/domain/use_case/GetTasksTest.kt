package com.example.flowdolist.feature_task.domain.use_case

import com.example.flowdolist.feature_task.data.repository.FakeTaskRepository
import com.example.flowdolist.feature_task.data.repository.TaskRepositoryImpl
import org.junit.Assert.*
import org.junit.Before

class GetTasksTest {

    private lateinit var getTasks: GetTasks
    private lateinit var fakeRepository: FakeTaskRepository

    @Before
    fun setUp() {
        fakeRepository = FakeTaskRepository()
        getTasks = GetTasks(fakeRepository)
    }

}

//we have made a fake repository do the rest some other day.