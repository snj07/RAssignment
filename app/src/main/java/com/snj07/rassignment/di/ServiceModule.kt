package com.snj07.rassignment.di

import com.snj07.rassignment.service.CustomJobServices
import com.snj07.rassignment.service.RemoteDataSourceImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract fun contributeRemoteDataSourceImpl(): RemoteDataSourceImpl


    @ContributesAndroidInjector
    abstract fun contributeCustomJobServices(): CustomJobServices
}