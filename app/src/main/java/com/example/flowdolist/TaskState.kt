package com.example.flowdolist

data class TaskState(
    val tasks: List<Task> = emptyList(),
    val taskName: String = "",
    val taskDescription: String = "",
    val isAddingTask: Boolean = false,
    val sortType: SortType = SortType.MOST_RECENT
)
