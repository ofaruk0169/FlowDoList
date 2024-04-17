package com.example.flowdolist.feature_task.presentation.tasks

import com.example.flowdolist.feature_task.domain.model.Task
import com.example.flowdolist.feature_task.domain.util.TaskOrder

sealed class TasksEvent {
    data class Order(val taskOrder: TaskOrder): TasksEvent()
    data class DeleteTask(val task: Task): TasksEvent()

    object RestoreTask: TasksEvent()
    //data class RestoreTask(val task: Task): TasksEvent()
    object ToggleOrderSection: TasksEvent()
}