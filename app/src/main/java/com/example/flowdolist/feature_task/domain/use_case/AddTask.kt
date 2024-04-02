package com.example.flowdolist.feature_task.domain.use_case

import com.example.flowdolist.feature_task.domain.model.InvalidNoteException
import com.example.flowdolist.feature_task.domain.model.Task
import com.example.flowdolist.feature_task.domain.repository.TaskRepository

class AddTask(
    private val repository: TaskRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(task: Task) {
        if(task.taskName.isBlank()){
            throw InvalidNoteException("The title of the task cannot be empty")
        }
        if(task.taskDescription.isBlank()) {
            throw InvalidNoteException("The description of the task cannot be empty")
        }
        repository.upsertTask(task)
    }
}