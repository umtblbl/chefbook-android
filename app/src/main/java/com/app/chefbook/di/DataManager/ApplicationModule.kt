package com.app.chefbook.di.DataManager

import android.app.Application
import android.content.Context
import com.app.chefbook.data.DataManager
import com.app.chefbook.data.IDataManager
import com.app.chefbook.data.Preferences.IPrefHelper
import com.app.chefbook.data.Preferences.PrefHelper
import com.app.chefbook.data.remote.apiHelper.IApiHelper
import com.app.chefbook.data.remote.apiHelper.ApiHelper
import com.app.chefbook.data.remote.serviceInterface.IUserService
import com.app.chefbook.data.remote.service.UserService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule constructor(application: Application) {

    var context:Context = application

    @Provides
    internal fun providesContext(): Context = context

    @Singleton
    @Provides
    internal fun providesDataManager(apiHelper: IApiHelper, prefHelper: PrefHelper):IDataManager = DataManager(apiHelper, prefHelper)

    @Singleton
    @Provides
    internal fun providesApiHelper(userService: UserService): IApiHelper = ApiHelper( userService)

    @Singleton
    @Provides
    internal fun providesPrefHelper(context: Context): IPrefHelper = PrefHelper(context)

    @Singleton
    @Provides
    internal fun providesUserService(): IUserService = UserService()


}