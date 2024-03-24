package com.example.flowdolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(
    private val dao: TaskDao
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.MOST_RECENT)
    private val _tasks = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.MOST_RECENT -> dao.getMostRecentTasks()
                SortType.OLDEST_TASKS -> dao.getOldestTasks()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(TaskState())

    fun onEvent(event: TaskEvent) {
        when(event) {
            is TaskEvent.DeleteTask -> {
                viewModelScope.launch {
                    dao.deleteTask(event.task)
                }
            }
            TaskEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingTask = false
                )}
            }
            TaskEvent.SaveTask -> {

            }
            is TaskEvent.SetTaskDescription -> {
                _state.update { it.copy(
                    taskName = event.taskDescription
                ) }
            }
            is TaskEvent.SetTaskName -> {
                _state.update { it.copy(
                    taskName = event.taskName
                ) }
            }
            TaskEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingTask = true
                ) }
            }
            is TaskEvent.SortTasks -> {
                _sortType.value = event.sortType
            }
        }
    }
}