package com.example.flowdolist.feature_task.domain.model

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Define your colors as top-level constants or within a companion object
val RED_ORANGE = Color.parseColor("#FF5733")
val LIGHT_GREEN = Color.parseColor("#90EE90")
val VIOLET = Color.parseColor("#EE82EE")
val BABY_BLUE = Color.parseColor("#89CFF0")
val RED_PINK = Color.parseColor("#FF69B4")


//This will define the field that our Task table should have

@Entity()
data class Task(
    val taskName: String,
    val taskDescription: String,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val taskCreatedAt: Long = System.currentTimeMillis(),
    val color: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    companion object {
        val taskColors = listOf(RED_ORANGE, LIGHT_GREEN, VIOLET, BABY_BLUE, RED_PINK)
    }
}

class InvalidNoteException(message: String): Exception(message)