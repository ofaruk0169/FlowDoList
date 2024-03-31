// ViewModelModule.kt

package com.example.flowdolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory
import javax.inject.Inject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.lifecycle.HiltViewModel

@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewModelModule {

    @Provides
    fun provideTaskViewModel(
        factory: ViewModelFactory
    ): TaskViewModel {
        return ViewModelProvider(factory).get(TaskViewModel::class.java)
    }
}
