package com.example.flowdolist.feature_task.domain.use_case

import com.example.flowdolist.feature_task.domain.model.InvalidTaskException
import com.example.flowdolist.feature_task.domain.model.Task
import com.example.flowdolist.feature_task.domain.repository.TaskRepository

class AddTask(
    private val repository: TaskRepository
) {

    @Throws(InvalidTaskException::class)
    suspend operator fun invoke(task: Task) {
        if(task.taskName.isBlank()){
            throw InvalidTaskException("The title of the task cannot be empty")
        }
        if(task.taskDescription.isBlank()) {
            throw InvalidTaskException("The description of the task cannot be empty")
        }
        repository.upsertTask(task)
    }
}

//homework, unit test this on your own.