package com.example.flowdolist.feature_task.domain.model

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flowdolist.ui.theme.*


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
        val taskColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidTaskException(message: String): Exception(message)