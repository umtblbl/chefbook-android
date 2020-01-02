package com.app.chefbook.Data.Remote.Interface

import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.Model.ServiceModel.RequestModel.ChangePassword
import com.app.chefbook.Model.ServiceModel.RequestModel.ChangeProfile
import com.app.chefbook.Model.ServiceModel.RequestModel.LoginUser
import com.app.chefbook.Model.ServiceModel.RequestModel.RegisterUser
import com.app.chefbook.Model.ServiceModel.ResponseModel.Profile
import com.app.chefbook.Model.ServiceModel.ResponseModel.ProfileDetails

interface IUserService {
    fun registerUser(registerUser: RegisterUser, callBack: ServiceCallBack<String>)
    fun loginUser(loginUser: LoginUser, callBack: ServiceCallBack<String>)
    fun getProfile(callBack: ServiceCallBack<Profile>)
    fun changePassword(changePassword: ChangePassword, callBack: ServiceCallBack<String>)
    fun getProfileDetails(callBack: ServiceCallBack<ProfileDetails>)
    fun changeProfile(changeProfile: ChangeProfile, callBack: ServiceCallBack<String>)
}
