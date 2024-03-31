package com.example.flowdolist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


//The DAO is an interface in Kotlin that basically defines how we want to
//interact with the database

@Dao
interface TaskDao  {

    @Upsert
    suspend fun upsertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM Task ORDER BY taskCreatedAt ASC")
    fun getOldestTasks(): Flow<List<Task>>

    @Query("SELECT * FROM Task ORDER BY taskCreatedAt DESC")
    fun getMostRecentTasks(): Flow<List<Task>>


}