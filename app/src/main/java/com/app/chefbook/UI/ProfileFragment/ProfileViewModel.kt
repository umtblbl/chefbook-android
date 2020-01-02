package com.app.chefbook.UI.ProfileFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.Data.DataManager
import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.Model.ServiceModel.ResponseModel.Profile

class ProfileViewModel (val dataManager: DataManager) : ViewModel() {

    var profile = MutableLiveData<Profile>()

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
}