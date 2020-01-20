package com.app.chefbook.data.remote.manager.userManager

import com.app.chefbook.data.preferences.PrefHelper
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.service.userService.UserService
import com.app.chefbook.model.serviceModel.requestModel.ChangePassword
import com.app.chefbook.model.serviceModel.requestModel.ChangeProfile
import com.app.chefbook.model.serviceModel.requestModel.LoginUser
import com.app.chefbook.model.serviceModel.requestModel.RegisterUser
import com.app.chefbook.model.serviceModel.responseModel.Profile
import com.app.chefbook.model.serviceModel.responseModel.ProfileDetails
import com.app.chefbook.model.serviceModel.responseModel.SearchResult
import com.app.chefbook.model.serviceModel.responseModel.UserFlowPost
import okhttp3.MultipartBody
import javax.inject.Inject

class UserManager @Inject constructor(userService: UserService, prefHelper: PrefHelper) :IUserManager {

    var userService: UserService? = userService
    var prefHelper: PrefHelper? = prefHelper

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

    override fun getUserFlowPost(callBack: ServiceCallBack<List<UserFlowPost>>) {
        userService?.getUserFlowPost(callBack)
    }

    override fun getSearchResult(q: String, callBack: ServiceCallBack<List<SearchResult>>) {
        userService?.getSearchResult(q, callBack)
    }

    override fun saveUdid(udid: String) {
        prefHelper?.saveUdid(udid)
    }

    override fun getUdid(): String? {
        return prefHelper?.getUdid()
    }

    override fun saveAuth(auth: String) {
        prefHelper?.saveUdid(auth)
    }

    override fun getAuth(): String? {
        return prefHelper?.getAuth()
    }

    override fun loginUser(loginUser: LoginUser, callBack: ServiceCallBack<String>) {
        userService?.loginUser(loginUser, callBack)
    }

}