package com.app.chefbook.Data.Remote

import com.app.chefbook.Data.Remote.Service.UserAuthService
import com.app.chefbook.Model.ServiceModel.RequestModel.LoginUser
import com.app.chefbook.Model.ServiceModel.RequestModel.RegisterUser
import javax.inject.Inject

class ApiHelper @Inject constructor (userAuthService: UserAuthService) : IApiHelper {

    var userAuthService: UserAuthService? = userAuthService

    override fun registerUser(registerUser: RegisterUser, callBack: ServiceCallBack<String>) {
        userAuthService?.registerUser(registerUser, callBack)
    }

    override fun loginUser(loginUser: LoginUser, callBack: ServiceCallBack<String>) {
        userAuthService?.loginUser(loginUser, callBack)
    }
}