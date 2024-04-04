package com.example.flowdolist.feature_task.domain.repository





//Call function of DAO via repository, directly accesses databases/api.
//Repo takes data sources and determines which one to forward to Use Cases

//e.g. get data from API & have caching layer mechanism in app
//repository needs to decide, load from cache or APi?
//decision logic and checking if errors from api/db and forward data to use cases
//use cases dont have to know where the data comes from

//why interface? Good for testing - create fake versions of repo
//we dont want to use our real api or our real db, which is security risk and slow
//we want test cases to be therfore fast, so we use fake to simulate behavour of repo
//we can pass fake repo to use cases, so use cases know what to use
//use cases dont care where teh data comes from

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