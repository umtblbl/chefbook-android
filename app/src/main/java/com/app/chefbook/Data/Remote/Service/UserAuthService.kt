package com.app.chefbook.Data.Remote.Service

import android.annotation.SuppressLint
import android.util.Log
import com.app.chefbook.Data.Remote.Retrofit.ApiInterface
import com.app.chefbook.Data.Remote.Interface.IUserAuthService
import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.Model.ServiceModel.RequestModel.LoginUser
import com.app.chefbook.Model.ServiceModel.RequestModel.RegisterUser
import com.app.denemeinstagramapp.Data.Retrofit.ApiClient
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response


class UserAuthService @Inject constructor() : IUserAuthService {

    var apiInterface: ApiInterface? = null

    init { apiInterface = ApiClient.client?.create(ApiInterface::class.java) }

    override fun registerUser(registerUser: RegisterUser, callBack: ServiceCallBack<String>) {

        apiInterface?.registerUser(registerUser)?.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                response.body()?.let { callBack.onResponse(it) }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** ERROR *** : UserAuthService-registerUser-onFailure #", "" + t.printStackTrace())
            }
        })
    }

    override fun loginUser(loginUser: LoginUser, callBack: ServiceCallBack<String>) {
        apiInterface?.loginUser(loginUser)?.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                response.body()?.let { callBack.onResponse(it) }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** ERROR *** : UserAuthService-registerUser-onFailure #", "" + t.printStackTrace())
            }
        })
    }
}
