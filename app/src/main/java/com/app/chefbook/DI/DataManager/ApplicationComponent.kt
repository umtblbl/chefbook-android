package com.app.chefbook.DI.DataManager

import com.app.chefbook.UI.LoginActivity.LoginActivity
import com.app.chefbook.UI.MainActivity.MainActivity
import com.app.chefbook.UI.RegisterActivity.RegisterActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(registerActivity: RegisterActivity)
    fun inject(loginActivity: LoginActivity)

}