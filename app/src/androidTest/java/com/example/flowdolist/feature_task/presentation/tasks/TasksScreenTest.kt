package com.example.flowdolist.feature_task.presentation.tasks

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flowdolist.core.util.TestTags
import com.example.flowdolist.di.AppModule
import com.example.flowdolist.feature_task.presentation.MainActivity
import com.example.flowdolist.feature_task.presentation.util.Screen
import com.example.flowdolist.ui.theme.FlowDoListTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class TasksScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            FlowDoListTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.TasksScreen.route
                ) {
                    composable(route = Screen.TasksScreen.route) {
                        TasksScreen(navController = navController)
                    }
                }
            }
        }
    }
    @Test
    fun clickToggleOrderSection_isVisibly() {
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertDoesNotExist()
        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertIsDisplayed()

    }
}