package com.app.chefbook.DI.DataManager

import com.app.chefbook.UI.LoginActivity.LoginActivity
import com.app.chefbook.UI.MainActivity.MainActivity
import com.app.chefbook.UI.PostInitiatorFragment.PostInitiatorFragment
import com.app.chefbook.UI.PostInitiatorFragment.PostInitiatorViewModel
import com.app.chefbook.UI.ProfileFragment.ProfileFragment
import com.app.chefbook.UI.ProfileFragment.ProfileViewModel
import com.app.chefbook.UI.RegisterActivity.RegisterActivity
import com.app.chefbook.UI.SettingsFragment.SettingsFragment
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