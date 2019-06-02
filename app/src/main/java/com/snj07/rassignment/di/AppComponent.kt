package com.snj07.rassignment.di

import android.app.Application
import com.snj07.rassignment.RAssignmentApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        AppModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        DBModule::class,
        RetrofitModule::class,
        ServiceModule::class

    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: RAssignmentApp)

}