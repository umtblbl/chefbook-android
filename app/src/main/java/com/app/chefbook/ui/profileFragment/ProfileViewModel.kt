package com.app.chefbook.ui.profileFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.data.DataManager
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.model.serviceModel.responseModel.Profile
import okhttp3.MultipartBody

class ProfileViewModel (val dataManager: DataManager) : ViewModel() {

    var profile = MutableLiveData<Profile>()
    var uploadProfilePictureState = MutableLiveData<String>()
    var uploadCoverPictureState = MutableLiveData<String>()

    fun getProfile() {
        dataManager.getProfile(object : ServiceCallBack<Profile> {
            override fun onResponse(response: Profile) {
                profile.postValue(response)
            }

            override fun onError(message: String) {
                Log.d("ServiceError","ProfileViewModel-getProfile -> $message")
            }
        })
    }

    fun uploadProfilePicture(profilePicture: MultipartBody.Part) {
        dataManager.uploadProfilePicture(profilePicture, object : ServiceCallBack<String> {
            override fun onResponse(response: String) {
               uploadProfilePictureState.postValue(response)
            }

            override fun onError(message: String) {
                uploadProfilePictureState.postValue(message)
            }
        })
    }

    fun uploadCoverPicture(coverPicture: MultipartBody.Part) {
        dataManager.uploadCoverPicture(coverPicture, object : ServiceCallBack<String> {
            override fun onResponse(response: String) {
                uploadCoverPictureState.postValue(response)
            }

            override fun onError(message: String) {
                uploadCoverPictureState.postValue(message)
            }
        })
    }
}