package com.example.flowdolist

sealed interface TaskEvent {
    object SaveTask: TaskEvent
    data class SetTaskName(val taskName: String): TaskEvent
    data class SetTaskDescription(val taskDescription: String): TaskEvent
    object ShowDialog: TaskEvent
    object HideDialog: TaskEvent
    data class SortTasks(val sortType: SortType): TaskEvent
    data class DeleteTask(val task: Task): TaskEvent

}