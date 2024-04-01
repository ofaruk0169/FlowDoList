package com.example.flowdolist.feature_task.presentation.tasks.components

import androidx.lifecycle.ViewModel
import com.example.flowdolist.feature_task.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

}