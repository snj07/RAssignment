package com.snj07.rassignment

import com.snj07.rassignment.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.realm.Realm


class RAssignmentApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {

        super.onCreate()
        Realm.init(this)
    }

}
