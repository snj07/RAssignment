package com.snj07.rassignment.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import com.snj07.rassignment.ui.base.ViewModelFactory
import com.snj07.rassignment.util.rx.AppSchedulerProvider
import com.snj07.rassignment.util.rx.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule.BindModule::class])
class AppModule {

    @Module
    interface BindModule {
        @Singleton
        @Binds
        fun bindContext(application: Application): Context

        @Binds
        fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

        @Singleton
        @Binds
        fun bindSchedulerProvider(schedulerProvider: AppSchedulerProvider): SchedulerProvider
    }


    @Singleton
    @Provides
    fun provideResoucre(context: Context): Resources {
        return context.resources
    }

}