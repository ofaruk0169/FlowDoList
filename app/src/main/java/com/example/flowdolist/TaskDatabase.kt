package com.example.flowdolist

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flowdolist.feature_task.domain.model.Task


// We have created the Table class to define the table
// We have created the DAO as an interface for the table
// Now we need to create the database (here) to connect the pieces together


@Database(
    //entities = our different tables within the database
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase: RoomDatabase() {

    abstract val dao: TaskDao
}