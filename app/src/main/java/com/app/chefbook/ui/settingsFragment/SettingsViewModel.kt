package com.app.chefbook.ui.settingsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.data.remote.manager.userManager.UserManager
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.model.serviceModel.requestModel.ChangePassword
import com.app.chefbook.model.serviceModel.requestModel.ChangeProfile
import com.app.chefbook.model.serviceModel.responseModel.ProfileDetails

class SettingsViewModel(val userManager: UserManager) : ViewModel() {

    var changePasswordState = MutableLiveData<String>()
    var changeProfileDetailsState = MutableLiveData<String>()
    var profileDetails = MutableLiveData<ProfileDetails>()
    var profileDetailsMessage: String = ""

    fun changePassword(changePassword: ChangePassword) {

        userManager.changePassword(changePassword, object : ServiceCallBack<String> {
            override fun onResponse(response: String) {
                changePasswordState.postValue(response)
            }

            override fun onError(message: String) {
                changePasswordState.postValue(message)
            }
        })
    }

    fun changeProfileDetails(changeProfile: ChangeProfile) {

        userManager.changeProfile(changeProfile, object : ServiceCallBack<String> {
            override fun onResponse(response: String) {
                changeProfileDetailsState.postValue(response)
            }

            override fun onError(message: String) {
                changeProfileDetailsState.postValue(message)
            }
        })
    }

    fun getProfileDetails() {
        userManager.getProfileDetails(object : ServiceCallBack<ProfileDetails> {
            override fun onResponse(response: ProfileDetails) {
                profileDetails.postValue(response)
            }

            override fun onError(message: String) {
                profileDetailsMessage = message
            }
        })
    }
}