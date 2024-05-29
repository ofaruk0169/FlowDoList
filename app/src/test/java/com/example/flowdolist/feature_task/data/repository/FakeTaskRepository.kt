package com.example.flowdolist.feature_task.data.repository

import com.example.flowdolist.feature_task.domain.model.Task
import com.example.flowdolist.feature_task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTaskRepository : TaskRepository {

    private val tasks = mutableListOf<Task>()

    override suspend fun upsertTask(task: Task) {
        tasks.add(task)
    }

    override suspend fun getTaskById(id: Int): Task? {
        return tasks.find { it.id == id }
    }

    override suspend fun deleteTask(task: Task) {
        tasks.remove(task)
    }

    override fun getOldestTasks(): Flow<List<Task>> {
        TODO("Not yet implemented")
    }

    //may need only this for testing
    override fun getMostRecentTasks(): Flow<List<Task>> {
        return flow { emit(tasks) }
    }
}