package com.example.flowdolist

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dagger.hilt.android.HiltAndroidApp


// We have created the Table class to define the table
// We have created the DAO as an interface for the table
// Now we need to create the database (here) to connect the pieces together


@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
@HiltAndroidApp
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}