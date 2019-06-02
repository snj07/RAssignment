package com.snj07.rassignment.di

import com.snj07.rassignment.repository.FacilityRepository
import com.snj07.rassignment.service.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideFilterRepository(remoteDataSource: RemoteDataSourceImpl): FacilityRepository {
        return FacilityRepository(remoteDataSource)
    }

}
