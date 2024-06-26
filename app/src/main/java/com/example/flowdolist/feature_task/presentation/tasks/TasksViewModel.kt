package com.example.flowdolist.feature_task.presentation.tasks

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.flowdolist.feature_task.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.flowdolist.feature_task.domain.model.Task
import com.example.flowdolist.feature_task.domain.util.OrderType
import com.example.flowdolist.feature_task.domain.util.TaskOrder
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TasksState())
    val state: State<TasksState> = _state

    private var recentlyDeletedTask: Task? = null

    private var getTasksJob: Job? = null

    init {
        getTasks(TaskOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: TasksEvent) {
        when(event) {
            is TasksEvent.Order -> {
                if(state.value.taskOrder::class == event.taskOrder::class &&
                    state.value.taskOrder.orderType == event.taskOrder.orderType
                    ) {
                        return
                }
                getTasks(event.taskOrder)
            }

            is TasksEvent.DeleteTask -> {
                viewModelScope.launch {
                    taskUseCases.deleteTasks(event.task)
                    recentlyDeletedTask = event.task
                }
            }
            is TasksEvent.RestoreTask -> {
                viewModelScope.launch {
                    taskUseCases.addTask(recentlyDeletedTask ?: return@launch)
                    recentlyDeletedTask = null
                }
            }

            is TasksEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getTasks(taskOrder: TaskOrder) {
        getTasksJob?.cancel()
        getTasksJob = taskUseCases.getTasks(taskOrder)
            .onEach { tasks ->
                _state.value = state.value.copy(
                    tasks = tasks,
                    taskOrder = taskOrder
                )
            }
            .launchIn(viewModelScope)
    }
}

