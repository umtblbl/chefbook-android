package com.app.chefbook.di.module

import android.app.Application
import android.content.Context
import com.app.chefbook.data.preferences.IPrefHelper
import com.app.chefbook.data.preferences.PrefHelper
import com.app.chefbook.data.remote.manager.userManager.UserManager
import com.app.chefbook.data.remote.manager.userManager.IUserManager
import com.app.chefbook.data.remote.service.userService.IUserService
import com.app.chefbook.data.remote.service.userService.UserService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserManagerModule constructor(application: Application){

    var context: Context = application

    @Provides
    internal fun providesContext(): Context = context

    @Singleton
    @Provides
    internal fun providesUserManager(userService: UserService, prefHelper: PrefHelper): IUserManager = UserManager(userService, prefHelper)

    @Singleton
    @Provides
    internal fun providesUserService(): IUserService =
        UserService()

    @Singleton
    @Provides
    internal fun providesPrefHelper(context: Context): IPrefHelper = PrefHelper(context)
}