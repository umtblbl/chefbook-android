package com.app.chefbook.Data

import com.app.chefbook.Data.Preferences.IPrefHelper
import com.app.chefbook.Data.Preferences.PrefHelper
import com.app.chefbook.Data.Remote.ApiHelper.IApiHelper
import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.Model.ServiceModel.RequestModel.ChangePassword
import com.app.chefbook.Model.ServiceModel.RequestModel.ChangeProfile
import com.app.chefbook.Model.ServiceModel.RequestModel.LoginUser
import com.app.chefbook.Model.ServiceModel.RequestModel.RegisterUser
import com.app.chefbook.Model.ServiceModel.ResponseModel.Profile
import com.app.chefbook.Model.ServiceModel.ResponseModel.ProfileDetails
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    override fun getProfile(callBack: ServiceCallBack<Profile>) {
        apiHelper?.getProfile(callBack)
    }

    override fun changePassword(changePassword: ChangePassword, callBack: ServiceCallBack<String>) {
        apiHelper?.changePassword(changePassword, callBack)
    }

    override fun getProfileDetails(callBack: ServiceCallBack<ProfileDetails>) {
        apiHelper?.getProfileDetails(callBack)
    }

    override fun changeProfile(changeProfile: ChangeProfile, callBack: ServiceCallBack<String>) {
        apiHelper?.changeProfile(changeProfile, callBack)
    }

    override fun uploadProfilePicture(profilePicture: MultipartBody.Part, callBack: ServiceCallBack<String>) {
        apiHelper?.uploadProfilePicture(profilePicture, callBack)
    }

    override fun uploadCoverPicture(coverPicture: MultipartBody.Part, callBack: ServiceCallBack<String>) {
        apiHelper?.uploadCoverPicture(coverPicture, callBack)
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