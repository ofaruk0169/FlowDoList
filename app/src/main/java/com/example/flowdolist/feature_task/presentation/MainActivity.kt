package com.example.flowdolist.feature_task.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.flowdolist.feature_task.data.data_source.TaskDatabase
import com.example.flowdolist.TaskScreen

import com.example.flowdolist.feature_task.presentation.add_edit_task.AddEditTaskEvent
import com.example.flowdolist.feature_task.presentation.add_edit_task.AddEditTaskScreen
import com.example.flowdolist.feature_task.presentation.tasks.TasksScreen
import com.example.flowdolist.feature_task.presentation.util.Screen
import com.example.flowdolist.ui.theme.FlowDoListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    //Dagger Hilt start
    /*
    private val db by lazy {

        Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "tasks.db"
        ).build()
    }*/


    //Dagger Hilt end

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

