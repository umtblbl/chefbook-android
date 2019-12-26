package com.app.chefbook.DI.DataManager

import android.app.Application


class DaggerApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()

    }
}