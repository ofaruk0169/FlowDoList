package com.example.flowdolist.feature_task.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.flowdolist.feature_task.data.data_source.TaskDatabase
import com.example.flowdolist.TaskScreen
import com.example.flowdolist.TaskViewModel
import com.example.flowdolist.ui.theme.FlowDoListTheme


class MainActivity : ComponentActivity() {


    //Dagger Hilt start

    private val db by lazy {

        Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "tasks.db"
        ).build()
    }
    private val viewModel by viewModels<TaskViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TaskViewModel(db.dao) as T
                }
            }
        }
    )

    //Dagger Hilt end


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowDoListTheme {
                val state by viewModel.state.collectAsState()
               // TaskScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}
