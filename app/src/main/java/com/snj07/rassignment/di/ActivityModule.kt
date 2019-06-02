package com.snj07.rassignment.di

import com.snj07.rassignment.di.annotation.ActivityScope
import com.snj07.rassignment.ui.main.MainActivity
import com.snj07.rassignment.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity


}
