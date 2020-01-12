package com.app.chefbook.Data.Remote.Interface

import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.model.serviceModel.requestModel.ChangePassword
import com.app.chefbook.model.serviceModel.requestModel.ChangeProfile
import com.app.chefbook.model.serviceModel.requestModel.LoginUser
import com.app.chefbook.model.serviceModel.requestModel.RegisterUser
import com.app.chefbook.model.serviceModel.responseModel.Profile
import com.app.chefbook.model.serviceModel.responseModel.ProfileDetails
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
}
