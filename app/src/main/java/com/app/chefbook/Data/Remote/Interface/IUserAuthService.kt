package com.app.chefbook.Data.Remote.Interface

import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.Model.ServiceModel.RequestModel.LoginUser
import com.app.chefbook.Model.ServiceModel.RequestModel.RegisterUser

interface IUserAuthService {
    fun registerUser(registerUser: RegisterUser, callBack: ServiceCallBack<String>)
    fun loginUser(loginUser: LoginUser, callBack: ServiceCallBack<String>)
}
