package com.example.flowdolist.feature_task.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.flowdolist.feature_task.domain.model.Task
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

    //May only need this one
    @Query("SELECT * FROM Task ORDER BY taskCreatedAt DESC")
    fun getMostRecentTasks(): Flow<List<Task>>


}