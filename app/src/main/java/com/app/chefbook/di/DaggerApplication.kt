package com.app.chefbook.di

import android.app.Application
import com.app.chefbook.di.module.UserManagerModule


class DaggerApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().userManagerModule(
            UserManagerModule(this)
        ).build()

    }
}