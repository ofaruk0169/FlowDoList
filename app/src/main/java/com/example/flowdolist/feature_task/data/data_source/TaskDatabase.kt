package com.example.flowdolist.feature_task.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flowdolist.feature_task.data.data_source.TaskDao
import com.example.flowdolist.feature_task.domain.model.Task

@Database(
    //entities = our different tables within the database
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase: RoomDatabase() {

    abstract val dao: TaskDao

    companion object {
        const val DATABASE_NAME = "tasks_db"
    }
}