package com.example.flowdolist.feature_task.presentation.add_edit_task

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flowdolist.feature_task.domain.model.Task
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditTaskScreen (
    navController: NavController,
    taskColor: Int,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    val titleState = viewModel.taskTitle.value
    val contentState = viewModel.taskContent.value

    val snackbarHostState = remember { SnackbarHostState() }

    val taskBackgroundAnimatable = remember {
        Animatable(
            Color(if(taskColor != -1) taskColor else viewModel.taskColor.value)
        )
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditTaskViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditTaskViewModel.UiEvent.SaveTask -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditTaskEvent.SaveTask)
                },
                //modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "Save Task")
            }
        },
        snackbarHost = { snackbarHostState }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(taskBackgroundAnimatable.value)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Task.taskColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.taskColor.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    taskBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditTaskEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))


            TextField(
                value = titleState.text,
                onValueChange = { newValue ->
                    viewModel.onEvent(AddEditTaskEvent.EnteredTitle(newValue))
                    // You can perform any actions you need with the new value here
                },
                label = { Text(titleState.hint) }, // Assuming titleState.hint contains the hint text
                textStyle = TextStyle(fontSize = 20.sp), // Set the desired font size here
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))


            TextField(
                value = contentState.text,
                onValueChange = { newValue ->
                    viewModel.onEvent(AddEditTaskEvent.EnteredContent(newValue))
                    // You can perform any actions you need with the new value here
                },
                label = { Text(contentState.hint) }, // Assuming contentState.hint contains the hint text
                textStyle = TextStyle(fontSize = 20.sp), // Set the desired font size here
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

