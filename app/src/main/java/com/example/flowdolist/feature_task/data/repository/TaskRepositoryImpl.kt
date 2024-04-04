package com.example.flowdolist.feature_task.data.repository

import com.example.flowdolist.feature_task.data.data_source.TaskDao
import com.example.flowdolist.feature_task.domain.model.Task
import com.example.flowdolist.feature_task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {
    override suspend fun upsertTask(task: Task) {
        dao.upsertTask(task)
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }

    override fun getOldestTasks(): Flow<List<Task>> {
        return dao.getOldestTasks()
    }

    //may only need this one
    override fun getMostRecentTasks(): Flow<List<Task>> {
        return dao.getMostRecentTasks()
    }
}