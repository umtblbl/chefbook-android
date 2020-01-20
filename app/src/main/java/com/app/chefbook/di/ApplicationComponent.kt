package com.app.chefbook.di

import com.app.chefbook.di.module.PostServiceModule
import com.app.chefbook.di.module.UserManagerModule
import com.app.chefbook.ui.loginActivity.LoginActivity
import com.app.chefbook.ui.postInitiatorFragment.PostInitiatorFragment
import com.app.chefbook.ui.profileFragment.ProfileFragment
import com.app.chefbook.ui.registerActivity.RegisterActivity
import com.app.chefbook.ui.discoveryFragment.DiscoveryFragment
import com.app.chefbook.ui.flowFragment.FlowFragment
import com.app.chefbook.ui.settingsFragment.SettingsFragment
import com.app.chefbook.utility.RateDialog
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [UserManagerModule::class, PostServiceModule::class])
interface ApplicationComponent {

    fun inject(registerActivity: RegisterActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(profileFragment: ProfileFragment)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(postInitiatorFragment: PostInitiatorFragment)
    fun inject(flowFragment: FlowFragment)
    fun inject(discoveryFragment: DiscoveryFragment)
    fun inject(rateDialog: RateDialog)
}
