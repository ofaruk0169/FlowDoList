package com.example.flowdolist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(
    state: TaskState,
    onEvent: (TaskEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {
            onEvent(TaskEvent.HideDialog) },
        modifier = modifier,
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Add Contact",
                    style = MaterialTheme.typography.headlineMedium
                )
                TextField(
                    value = state.taskName,
                    onValueChange = {
                        onEvent(TaskEvent.SetTaskName(it))
                    },
                    placeholder = {
                        Text(text = "First Name")
                    }
                )

                TextField(
                    value = state.taskDescription,
                    onValueChange = {
                        onEvent(TaskEvent.SetTaskDescription(it))
                    },
                    placeholder = {
                        Text(text = "Description")
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            onEvent(TaskEvent.SaveTask)
                        }
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    )
}


