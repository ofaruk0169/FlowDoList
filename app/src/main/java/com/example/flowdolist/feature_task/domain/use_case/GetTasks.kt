package com.example.flowdolist.feature_task.domain.use_case

import com.example.flowdolist.feature_task.domain.model.Task
import com.example.flowdolist.feature_task.domain.repository.TaskRepository
import com.example.flowdolist.feature_task.domain.util.OrderType
import com.example.flowdolist.feature_task.domain.util.TaskOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTasks(
    private val repository: TaskRepository
) {
    operator fun invoke(
        //test - return correct order, if we pass the correct order
        taskOrder: TaskOrder = TaskOrder.Date(OrderType.Descending)
    ): Flow<List<Task>> {
        return repository.getMostRecentTasks().map { tasks ->
            when(taskOrder.orderType) {
                is OrderType.Ascending -> {
                    when(taskOrder) {
                        is TaskOrder.Title -> tasks.sortedBy { it.taskName.lowercase() }
                        is TaskOrder.Date -> tasks.sortedBy { it.taskCreatedAt }
                        is TaskOrder.Color -> tasks.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(taskOrder) {
                        is TaskOrder.Title -> tasks.sortedByDescending { it.taskName.lowercase() }
                        is TaskOrder.Date -> tasks.sortedByDescending { it.taskCreatedAt }
                        is TaskOrder.Color -> tasks.sortedByDescending { it.color }

                    }
                }
            }
        }
    }
}