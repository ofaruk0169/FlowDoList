package com.example.flowdolist

//import com.example.flowdolist.feature_task.domain.model.LIGHT_GREEN
import com.example.flowdolist.feature_task.domain.model.Task


//Class that tracks our screen state, e.g. the task list that is currently
//displayed, or the sort type that is currently selected

data class TaskState(
    val tasks: List<Task> = emptyList(),
    val taskName: String = "",
    val taskDescription: String = "",
    //val taskColor: Int = LIGHT_GREEN,
    val isAddingTask: Boolean = false,
    val sortType: SortType = SortType.MOST_RECENT
)
