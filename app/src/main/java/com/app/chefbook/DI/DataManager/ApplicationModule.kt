package com.app.chefbook.DI.DataManager

import android.app.Application
import android.content.Context
import com.app.chefbook.Data.DataManager
import com.app.chefbook.Data.IDataManager
import com.app.chefbook.Data.Preferences.IPrefHelper
import com.app.chefbook.Data.Preferences.PrefHelper
import com.app.chefbook.Data.Remote.IApiHelper
import com.app.chefbook.Data.Remote.ApiHelper
import com.app.chefbook.Data.Remote.Interface.IUserAuthService
import com.app.chefbook.Data.Remote.Service.UserAuthService
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
    internal fun providesApiHelper(userAuthService: UserAuthService): IApiHelper = ApiHelper(userAuthService)

    @Singleton
    @Provides
    internal fun providesPrefHelper(context: Context): IPrefHelper = PrefHelper(context)

    @Singleton
    @Provides
    internal fun providesUserAuthService(): IUserAuthService = UserAuthService()

}