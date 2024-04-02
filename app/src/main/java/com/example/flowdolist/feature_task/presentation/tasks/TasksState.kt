package com.example.flowdolist.feature_task.presentation.tasks

import com.example.flowdolist.feature_task.domain.model.Task
import com.example.flowdolist.feature_task.domain.util.OrderType
import com.example.flowdolist.feature_task.domain.util.TaskOrder

data class TasksState(
    val tasks: List<Task> = emptyList(),
    val taskOrder: TaskOrder = TaskOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
