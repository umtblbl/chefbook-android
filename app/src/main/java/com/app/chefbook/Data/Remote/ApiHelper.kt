package com.app.chefbook.Data.Remote

import com.app.chefbook.Data.Remote.Service.ProfileService
import com.app.chefbook.Data.Remote.Service.UserAuthService
import com.app.chefbook.Model.ServiceModel.RequestModel.LoginUser
import com.app.chefbook.Model.ServiceModel.RequestModel.RegisterUser
import com.app.chefbook.Model.ServiceModel.ResponseModel.Profile
import javax.inject.Inject

class ApiHelper @Inject constructor (userAuthService: UserAuthService, profileService: ProfileService) : IApiHelper {

    var userAuthService: UserAuthService? = userAuthService
    var profileService: ProfileService? = profileService

    override fun registerUser(registerUser: RegisterUser, callBack: ServiceCallBack<String>) {
        userAuthService?.registerUser(registerUser, callBack)
    }

    override fun getProfile(accessToken: String, callBack: ServiceCallBack<Profile>) {
        profileService?.getProfile(accessToken, callBack)
    }

    override fun loginUser(loginUser: LoginUser, callBack: ServiceCallBack<String>) {
        userAuthService?.loginUser(loginUser, callBack)
    }

}