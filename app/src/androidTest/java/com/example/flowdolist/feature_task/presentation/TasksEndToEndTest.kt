package com.example.flowdolist.feature_task.presentation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flowdolist.di.AppModule
import com.example.flowdolist.feature_task.presentation.add_edit_task.AddEditTaskScreen
import com.example.flowdolist.feature_task.presentation.tasks.TasksScreen
import com.example.flowdolist.feature_task.presentation.util.Screen
import com.example.flowdolist.ui.theme.FlowDoListTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class TasksEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            FlowDoListTheme {
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

    @Test
    fun saveNewTask_editAfterwards() {
        composeRule.onNodeWithContentDescription("Add").performClick()


    }
}