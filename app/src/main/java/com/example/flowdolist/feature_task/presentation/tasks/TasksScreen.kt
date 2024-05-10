package com.example.flowdolist.feature_task.presentation.tasks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flowdolist.feature_task.presentation.tasks.components.OrderSection
import com.example.flowdolist.feature_task.presentation.tasks.components.TaskItem
import com.example.flowdolist.feature_task.presentation.util.Screen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TasksViewModel = hiltViewModel()
) {
    // Retrieve the state from the view model
    val state = viewModel.state.value

    val snackbarHostState = remember {
        SnackbarHostState()
    }


    // Remember the coroutine scope
    val scope = rememberCoroutineScope()

    // Define the scaffold layout
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        floatingActionButton = {
            // Floating action button for adding tasks
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditTaskScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
            }
        },


        ) { paddingValues ->
        // Apply padding provided by the Scaffold to the Column
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Go with the flow and do your todos",
                    style = MaterialTheme.typography.headlineSmall
                )
                IconButton(
                    onClick = {
                           viewModel.onEvent(TasksEvent.ToggleOrderSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Sort,
                        contentDescription = "Sort"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    taskOrder = state.taskOrder,
                    onOrderChange = {
                        viewModel.onEvent(TasksEvent.Order(it))
                    }
                )
            }


            Spacer(modifier = Modifier.height(16.dp))


            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.tasks) { task ->
                    TaskItem(
                        task = task,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color(task.color))
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditTaskScreen.route +
                                            "?taskId=${task.id}&taskColor=${task.color}"
                                )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(TasksEvent.DeleteTask(task))
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Task deleted",
                                    actionLabel = "Undo"
                                )


                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(TasksEvent.RestoreTask)
                                }

                            }
                        },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}