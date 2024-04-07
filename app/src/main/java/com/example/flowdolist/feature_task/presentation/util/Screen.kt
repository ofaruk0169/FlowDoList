package com.example.flowdolist.feature_task.presentation.util

sealed class Screen(val route: String) {
    object TasksScreen: Screen("tasks_screen")
    object AddEditTaskScreen: Screen("add_edit_tasks_screen")
}