package com.example.flowdolist.feature_task.domain.use_case

data class TaskUseCases(
    val getTasks: GetTasks,
    val deleteTasks: DeleteTask,
    val addTask: AddTask,
    val getTask: GetTask
)
