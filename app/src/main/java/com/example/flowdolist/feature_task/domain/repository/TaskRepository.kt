package com.example.flowdolist.feature_task.domain.repository


import com.example.flowdolist.feature_task.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun upsertTask(task: Task)

    suspend fun getTaskById(id: Int): Task?

    suspend fun deleteTask(task: Task)

    fun getOldestTasks(): Flow<List<Task>>

    //may only need this one
    fun getMostRecentTasks(): Flow<List<Task>>
}