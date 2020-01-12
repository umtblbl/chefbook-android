package com.app.chefbook.ui.SettingsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.Data.DataManager
import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.model.serviceModel.requestModel.ChangePassword
import com.app.chefbook.model.serviceModel.requestModel.ChangeProfile
import com.app.chefbook.model.serviceModel.responseModel.ProfileDetails

class SettingsViewModel(val dataManager: DataManager) : ViewModel() {

    var changePasswordState = MutableLiveData<String>()
    var changeProfileDetailsState = MutableLiveData<String>()
    var profileDetails = MutableLiveData<ProfileDetails>()
    var profileDetailsMessage: String = ""

    fun changePassword(changePassword: ChangePassword) {

        dataManager.changePassword(changePassword, object : ServiceCallBack<String> {
            override fun onResponse(response: String) {
                changePasswordState.postValue(response)
            }

            override fun onError(message: String) {
                changePasswordState.postValue(message)
            }
        })
    }

    fun changeProfileDetails(changeProfile: ChangeProfile) {

        dataManager.changeProfile(changeProfile, object : ServiceCallBack<String> {
            override fun onResponse(response: String) {
                changeProfileDetailsState.postValue(response)
            }

            override fun onError(message: String) {
                changeProfileDetailsState.postValue(message)
            }
        })
    }

    fun getProfileDetails() {
        dataManager.getProfileDetails(object : ServiceCallBack<ProfileDetails> {
            override fun onResponse(response: ProfileDetails) {
                profileDetails.postValue(response)
            }

            override fun onError(message: String) {
                profileDetailsMessage = message
            }
        })
    }
}