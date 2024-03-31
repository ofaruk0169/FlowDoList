package com.example.flowdolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//This will define the field that our Task table should have

@Entity()
data class Task(
    val taskName: String,
    val taskDescription: String,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val taskCreatedAt: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
}