package com.example.flowdolist.feature_task.presentation.tasks

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.flowdolist.di.AppModule
import com.example.flowdolist.feature_task.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Rule


@HiltAndroidTest
@UninstallModules(AppModule::class)
class TasksScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()
}