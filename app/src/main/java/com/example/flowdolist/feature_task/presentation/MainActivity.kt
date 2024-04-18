package com.example.flowdolist.feature_task.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flowdolist.feature_task.presentation.add_edit_task.AddEditTaskScreen
import com.example.flowdolist.feature_task.presentation.tasks.TasksScreen
import com.example.flowdolist.feature_task.presentation.util.Screen
import com.example.flowdolist.ui.theme.FlowDoListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                FlowDoListTheme {
                    Surface(
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.TasksScreen.route
                        ) {
                            composable(route = Screen.TasksScreen.route) {
                                TasksScreen(navController = navController)
                            }
                            composable(
                                route = Screen.AddEditTaskScreen.route +
                                        "?taskId={taskId}&taskColor={taskColor}",
                                arguments = listOf(
                                    navArgument(
                                        name = "taskId"
                                    ) {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    },
                                    navArgument(
                                        name = "taskColor"
                                    ) {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    },
                                )
                            ) {
                                val color = it.arguments?.getInt("taskColor") ?: -1
                                AddEditTaskScreen(
                                    navController = navController,
                                    taskColor = color
                                )
                            }
                        }
                    }
                }
            }
        }
    }

