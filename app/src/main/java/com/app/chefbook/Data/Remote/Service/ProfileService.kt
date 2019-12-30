package com.app.chefbook.Data.Remote.Service

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.app.chefbook.Data.Preferences.IPrefHelper
import com.app.chefbook.Data.Preferences.PrefHelper
import com.app.chefbook.Data.Remote.Retrofit.ApiInterface
import com.app.chefbook.Data.Remote.Interface.IProfileService
import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.Model.ServiceModel.ResponseModel.Profile
import com.app.denemeinstagramapp.Data.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProfileService @Inject constructor(prefHelper: IPrefHelper) : IProfileService {

    var apiInterface: ApiInterface? = null

    init {
        //prefHelper.getAuth()?.let { ApiClient.setAccessToken(accessToken = it) }
        apiInterface = ApiClient.client?.create(ApiInterface::class.java)
    }

    override fun getProfile(accessToken: String, callBack: ServiceCallBack<Profile>) {
        apiInterface?.getProfile()?.enqueue(object : Callback<Profile> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                if (response.isSuccessful)
                    response.body()?.let { callBack.onResponse(it) }
                else
                    Log.e("# *** ERROR *** : ProfileService-getProfile-notSuccessful #","" + response.message())
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<Profile>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** ERROR *** : ProfileService-getProfile-onFailure #","" + t.printStackTrace())
            }
        })
    }
}