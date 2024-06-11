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

    //This line declares a read-only property id of type Int and initializes
// it to 0.
    //Since id is the primary key and autoGenerate is set to true, the initial
// value of 0 is just a placeholder. When the Task object is inserted into the
// database, the database will ignore the 0 and generate a unique value for id.
) {
    companion object {
        val taskColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
    //for color bubbles to change notes color
}

class InvalidTaskException(message: String): Exception(message)