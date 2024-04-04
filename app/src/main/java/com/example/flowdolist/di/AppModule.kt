package com.example.flowdolist.di

import android.app.Application
import androidx.room.Room
import com.example.flowdolist.TaskEvent
import com.example.flowdolist.feature_task.data.data_source.TaskDatabase
import com.example.flowdolist.feature_task.data.repository.TaskRepositoryImpl
import com.example.flowdolist.feature_task.domain.repository.TaskRepository
import com.example.flowdolist.feature_task.domain.use_case.AddTask
import com.example.flowdolist.feature_task.domain.use_case.DeleteTask
import com.example.flowdolist.feature_task.domain.use_case.GetTask
import com.example.flowdolist.feature_task.domain.use_case.GetTasks
import com.example.flowdolist.feature_task.domain.use_case.TaskUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getTasks = GetTasks(repository),
            deleteTasks = DeleteTask(repository),
            addTask = AddTask(repository),
            getTask = GetTask(repository)
        )
    }
}