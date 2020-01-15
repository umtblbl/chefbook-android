package com.app.chefbook.di.PostService

import android.app.Application
import android.content.Context
import com.app.chefbook.data.remote.service.UserService
import com.app.chefbook.data.remote.serviceInterface.IUserService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PostServiceModule constructor(application: Application) {

    var context: Context = application

    @Provides
    internal fun providesContext(): Context = context

    @Singleton
    @Provides
    internal fun providesUserService(): IUserService = UserService()

}
