package com.app.chefbook.di.DataManager

import com.app.chefbook.ui.LoginActivity.LoginActivity
import com.app.chefbook.ui.MainActivity.MainActivity
import com.app.chefbook.ui.postInitiatorFragment.PostInitiatorFragment
import com.app.chefbook.ui.profileFragment.ProfileFragment
import com.app.chefbook.ui.RegisterActivity.RegisterActivity
import com.app.chefbook.ui.settingsFragment.SettingsFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(registerActivity: RegisterActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(profileFragment: ProfileFragment)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(postInitiatorFragment: PostInitiatorFragment)

}