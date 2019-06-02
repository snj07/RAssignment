package com.snj07.rassignment.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.snj07.rassignment.db.DetailDB
import com.snj07.rassignment.db.RadiusRealmScheme
import com.snj07.rassignment.db.RadiusSharedPreferenceManager
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
class DBModule {

    companion object {
        private const val TAG = "database_radius"
        private const val PREF_NAME = "$TAG.pref"
        private const val REALM_NAME = "$TAG.realm"
        private const val REALM_SCHEME_VERSION = 1L
    }


    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }


    @Singleton
    @Provides
    fun provideRadiusSharePreferencesManager(sharedPreferences: SharedPreferences): RadiusSharedPreferenceManager {
        return RadiusSharedPreferenceManager(sharedPreferences)
    }


    @Singleton
    @Provides
    fun provideRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder()
            .schemaVersion(REALM_SCHEME_VERSION)
            .name(REALM_NAME)
            .modules(RadiusRealmScheme())
            .deleteRealmIfMigrationNeeded()
//            .migration(RadiusRealmMigration())
            .compactOnLaunch()
            .build()
    }


    @Singleton
    @Provides
    fun provideDetailDB(realmConfiguration: RealmConfiguration): DetailDB {
        return DetailDB(realmConfiguration)
    }
}