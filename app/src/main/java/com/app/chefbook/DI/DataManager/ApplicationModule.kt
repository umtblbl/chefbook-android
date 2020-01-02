package com.app.chefbook.DI.DataManager

import android.app.Application
import android.content.Context
import com.app.chefbook.Data.DataManager
import com.app.chefbook.Data.IDataManager
import com.app.chefbook.Data.Preferences.IPrefHelper
import com.app.chefbook.Data.Preferences.PrefHelper
import com.app.chefbook.Data.Remote.ApiHelper.IApiHelper
import com.app.chefbook.Data.Remote.ApiHelper.ApiHelper
import com.app.chefbook.Data.Remote.Interface.IUserService
import com.app.chefbook.Data.Remote.Service.UserService
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