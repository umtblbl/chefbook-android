package com.app.chefbook.Data.Remote.ApiHelper

import com.app.chefbook.Data.Remote.Service.UserService
import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.model.serviceModel.requestModel.ChangePassword
import com.app.chefbook.model.serviceModel.requestModel.ChangeProfile
import com.app.chefbook.model.serviceModel.requestModel.LoginUser
import com.app.chefbook.model.serviceModel.requestModel.RegisterUser
import com.app.chefbook.model.serviceModel.responseModel.Profile
import com.app.chefbook.model.serviceModel.responseModel.ProfileDetails
import okhttp3.MultipartBody
import javax.inject.Inject

class ApiHelper @Inject constructor (userService: UserService) : IApiHelper {

    var userService: UserService? = userService

    override fun registerUser(registerUser: RegisterUser, callBack: ServiceCallBack<String>) {
        userService?.registerUser(registerUser, callBack)
    }

    override fun getProfile(callBack: ServiceCallBack<Profile>) {
        userService?.getProfile(callBack)
    }

    override fun changePassword(changePassword: ChangePassword, callBack: ServiceCallBack<String>) {
        userService?.changePassword(changePassword, callBack)
    }

    override fun getProfileDetails(callBack: ServiceCallBack<ProfileDetails>) {
        userService?.getProfileDetails(callBack)
    }

    override fun changeProfile(changeProfile: ChangeProfile, callBack: ServiceCallBack<String>) {
        userService?.changeProfile(changeProfile, callBack)
    }

    override fun uploadProfilePicture(picture: MultipartBody.Part, callBack: ServiceCallBack<String>) {
        userService?.uploadProfilePicture(picture, callBack)
    }

    override fun uploadCoverPicture(coverPicture: MultipartBody.Part, callBack: ServiceCallBack<String>) {
        userService?.uploadCoverPicture(coverPicture, callBack)
    }

    override fun loginUser(loginUser: LoginUser, callBack: ServiceCallBack<String>) {
        userService?.loginUser(loginUser, callBack)
    }
}