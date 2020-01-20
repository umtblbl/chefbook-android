package com.app.chefbook.ui.profileFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.data.remote.manager.userManager.UserManager
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.model.serviceModel.responseModel.Profile
import okhttp3.MultipartBody

class ProfileViewModel (val userManager: UserManager) : ViewModel() {

    var profile = MutableLiveData<Profile>()
    var profileState = MutableLiveData<Boolean>()
    var uploadProfilePictureState = MutableLiveData<String>()
    var uploadCoverPictureState = MutableLiveData<String>()

    fun getProfile() {
        profileState.postValue(true)
        Log.e("profile-API", "true")
        userManager.getProfile(object : ServiceCallBack<Profile> {
            override fun onResponse(response: Profile) {
                profile.postValue(response)
                Log.e("profile-API", "false")

                profileState.postValue(false)
            }

            override fun onError(message: String) {
                profileState.postValue(false)
                Log.e("profile-API", "false")

                Log.d("ServiceError","ProfileViewModel-getProfile -> $message")
            }
        })
    }

    fun uploadProfilePicture(profilePicture: MultipartBody.Part) {
        userManager.uploadProfilePicture(profilePicture, object : ServiceCallBack<String> {
            override fun onResponse(response: String) {
               uploadProfilePictureState.postValue(response)
            }

            override fun onError(message: String) {
                uploadProfilePictureState.postValue(message)
            }
        })
    }

    fun uploadCoverPicture(coverPicture: MultipartBody.Part) {
        userManager.uploadCoverPicture(coverPicture, object : ServiceCallBack<String> {
            override fun onResponse(response: String) {
                uploadCoverPictureState.postValue(response)
            }

            override fun onError(message: String) {
                uploadCoverPictureState.postValue(message)
            }
        })
    }
}