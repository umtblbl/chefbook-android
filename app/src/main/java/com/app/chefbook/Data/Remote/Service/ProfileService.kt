package com.app.chefbook.Data.Remote.Service

import android.annotation.SuppressLint
import android.util.Log
import com.app.chefbook.Data.Remote.ApiInterface
import com.app.chefbook.Data.Remote.Interface.IProfileService
import com.app.chefbook.Data.Remote.Interface.IUserAuthService
import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.Model.ServiceModel.ResponseModel.Profile
import com.app.denemeinstagramapp.Data.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProfileService @Inject constructor() : IProfileService {

    var apiInterface: ApiInterface? = null

    init { apiInterface = ApiClient.client?.create(ApiInterface::class.java) }

    override fun getProfile(accessToken: String, callBack: ServiceCallBack<Profile>) {
        apiInterface?.getProfile(accessToken)?.enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                response.body()?.let { callBack.onResponse(it) }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<Profile>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** ERROR *** : ProfileService-getProfile-onFailure #", "" + t.printStackTrace())
            }
        })
    }
}