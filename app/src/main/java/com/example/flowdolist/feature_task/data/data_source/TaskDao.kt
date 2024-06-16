package com.example.flowdolist.feature_task.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.flowdolist.feature_task.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao  {

    @Upsert
    suspend fun upsertTask(task: Task)

    @Query("SELECT * FROM Task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM Task ORDER BY taskCreatedAt ASC")
    fun getOldestTasks(): Flow<List<Task>>

    //TODOMay only need this one
    @Query("SELECT * FROM Task ORDER BY taskCreatedAt DESC")
    fun getMostRecentTasks(): Flow<List<Task>>

}