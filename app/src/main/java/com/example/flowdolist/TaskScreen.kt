package com.example.flowdolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskScreen(
    state: TaskState,
    onEvent: (TaskEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(TaskEvent.ShowDialog)
             }) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Contact"
                )
            }
        },
        modifier = Modifier.padding(16.dp)
    ) { paddingValues ->
        if(state.isAddingTask) {
            AddTaskDialog(state = state, onEvent = onEvent)
        }
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SortType.values().forEach { sortType ->
                            Row(
                                modifier = Modifier
                                    .clickable {
                                        onEvent(TaskEvent.SortTasks(sortType))
                                    },
                                verticalAlignment = CenterVertically
                            ) {
                                RadioButton(selected = state.sortType == sortType,
                                    onClick = {
                                        onEvent(TaskEvent.SortTasks(sortType))
                                    }
                                )
                                Text(text = sortType.name)
                            }
                        }

                    }
                }
                items(state.tasks) { tasks ->
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "${tasks.taskName}",
                                fontSize = 20.sp
                            )
                            Text(text = tasks.taskDescription, fontSize = 12.sp)
                        }
                        IconButton(onClick = {
                            onEvent(TaskEvent.DeleteTask(tasks))
                        }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Tasks"
                            )
                        }
                    }
                }
            }
    }
}