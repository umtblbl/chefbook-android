package com.app.chefbook.UI.ProfileFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.Data.DataManager
import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.Model.ServiceModel.ResponseModel.Profile
import okhttp3.MultipartBody
import okhttp3.RequestBody

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