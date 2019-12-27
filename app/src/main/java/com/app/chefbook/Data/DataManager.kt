package com.app.chefbook.Data

import com.app.chefbook.Data.Preferences.IPrefHelper
import com.app.chefbook.Data.Preferences.PrefHelper
import com.app.chefbook.Data.Remote.IApiHelper
import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.Model.ServiceModel.RequestModel.LoginUser
import com.app.chefbook.Model.ServiceModel.RequestModel.RegisterUser
import javax.inject.Inject

class DataManager @Inject constructor(apiHelper: IApiHelper, prefHelper: PrefHelper) : IDataManager {

    private var apiHelper: IApiHelper? = apiHelper
    private val prefHelper: IPrefHelper? = prefHelper

    override fun registerUser(registerUser: RegisterUser, callBack: ServiceCallBack<String>) {
        apiHelper?.registerUser(registerUser, callBack)
    }

    override fun loginUser(loginUser: LoginUser, callBack: ServiceCallBack<String>) {
        apiHelper?.loginUser(loginUser, callBack)
    }

    override fun saveUdid(udid: String) {
        prefHelper!!.saveUdid(udid)
    }

    override fun getUdid(): String? {
        return prefHelper!!.getUdid()
    }

    override fun saveAuth(auth: String) {
        prefHelper!!.saveAuth(auth)
    }

    override fun getAuth(): String? {
        return prefHelper!!.getAuth()
    }

}