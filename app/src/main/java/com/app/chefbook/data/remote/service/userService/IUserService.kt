package com.app.chefbook.data.remote.service.userService

import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.model.serviceModel.requestModel.*
import com.app.chefbook.model.serviceModel.responseModel.Profile
import com.app.chefbook.model.serviceModel.responseModel.ProfileDetails
import com.app.chefbook.model.serviceModel.responseModel.SearchResult
import com.app.chefbook.model.serviceModel.responseModel.UserFlowPost
import okhttp3.MultipartBody

interface IUserService {
    fun registerUser(registerUser: RegisterUser, callBack: ServiceCallBack<String>)
    fun loginUser(loginUser: LoginUser, callBack: ServiceCallBack<String>)
    fun getProfile(callBack: ServiceCallBack<Profile>)
    fun changePassword(changePassword: ChangePassword, callBack: ServiceCallBack<String>)
    fun getProfileDetails(callBack: ServiceCallBack<ProfileDetails>)
    fun changeProfile(changeProfile: ChangeProfile, callBack: ServiceCallBack<String>)
    fun uploadProfilePicture(profilePicture: MultipartBody.Part, callBack: ServiceCallBack<String>)
    fun uploadCoverPicture(coverPicture: MultipartBody.Part, callBack: ServiceCallBack<String>)
    fun getUserFlowPost(callBack: ServiceCallBack<List<UserFlowPost>>)
    fun getSearchResult(q: String, callBack: ServiceCallBack<List<SearchResult>>)
}
