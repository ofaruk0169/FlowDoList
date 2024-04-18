package com.example.flowdolist.feature_task.presentation.tasks.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flowdolist.feature_task.domain.util.OrderType
import com.example.flowdolist.feature_task.domain.util.TaskOrder


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    taskOrder: TaskOrder = TaskOrder.Date(OrderType.Descending),
    onOrderChange: (TaskOrder) -> Unit,

) {
    Column(
        modifier = modifier
            //.background(Color.Gray)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),

        ) {
            DefaultRadioButton(
                text = "Title",
                selected = taskOrder is TaskOrder.Title,
                onSelect = { onOrderChange(TaskOrder.Title(taskOrder.orderType)) },

            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = taskOrder is TaskOrder.Date,
                onSelect = { onOrderChange(TaskOrder.Date(taskOrder.orderType)) }
            )
            DefaultRadioButton(
                text = "Color",
                selected = taskOrder is TaskOrder.Color,
                onSelect = { onOrderChange(TaskOrder.Color(taskOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = taskOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(taskOrder.copy(OrderType.Ascending))
                }
            )
            DefaultRadioButton(
                text = "Descending",
                selected = taskOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(taskOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}