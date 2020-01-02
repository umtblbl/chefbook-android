package com.app.chefbook.Data.Remote.Service

import android.annotation.SuppressLint
import android.util.Log
import coStringm.app.chefbook.Data.Remote.ApiClient.ApiInterface
import com.app.chefbook.Data.Remote.Interface.IUserService
import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.Model.ServiceModel.RequestModel.ChangePassword
import com.app.chefbook.Model.ServiceModel.RequestModel.ChangeProfile
import com.app.chefbook.Model.ServiceModel.RequestModel.LoginUser
import com.app.chefbook.Model.ServiceModel.RequestModel.RegisterUser
import com.app.chefbook.Model.ServiceModel.ResponseModel.Profile
import com.app.chefbook.Model.ServiceModel.ResponseModel.ProfileDetails
import com.app.denemeinstagramapp.Data.Retrofit.ApiClient
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response


class UserService @Inject constructor() : IUserService {

    private var apiInterface: ApiInterface? = null

    init { apiInterface = ApiClient.client?.create(ApiInterface::class.java) }

    override fun registerUser(registerUser: RegisterUser, callBack: ServiceCallBack<String>) {

        apiInterface?.registerUser(registerUser)?.enqueue(object : Callback<String> {

            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful)
                    response.body()?.let { callBack.onResponse(it) }
                else {
                    response.message().let { callBack.onError(it) }
                    Log.e("# *** API ERROR *** : UserService-registerUser-notSuccessful #","" + response.message())
                }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** API ERROR *** : UserService-registerUser-onFailure #", "" + t.printStackTrace())
            }
        })
    }

    override fun loginUser(loginUser: LoginUser, callBack: ServiceCallBack<String>) {

        apiInterface?.loginUser(loginUser)?.enqueue(object : Callback<String> {

            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful)
                    response.body()?.let { callBack.onResponse(it) }
                else {
                    response.message().let { callBack.onError(it) }
                    Log.e("# *** API ERROR *** : ProfileService-getProfile-notSuccessful #","" + response.message())
                }
            }
            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** API ERROR *** : UserService-loginUser-onFailure #", "" + t.printStackTrace())
            }
        })
    }

    override fun getProfile(callBack: ServiceCallBack<Profile>) {

        apiInterface?.getProfile()?.enqueue(object : Callback<Profile> {

            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                if (response.isSuccessful)
                    response.body()?.let { callBack.onResponse(it) }
                else {
                    response.message().let { callBack.onError(it) }
                    Log.e("# *** API ERROR *** : ProfileService-getProfile-notSuccessful #","" + response.message())
                }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<Profile>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** API ERROR *** : ProfileService-getProfile-onFailure #","" + t.printStackTrace())
            }
        })
    }

    override fun changePassword(changePassword: ChangePassword, callBack: ServiceCallBack<String>) {
        apiInterface?.changePassword(changePassword)?.enqueue(object : Callback<String> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful) {
                    response.raw().code().let { callBack.onResponse(it.toString()) }
                }
                else {
                    response.raw().code().let { callBack.onError(it.toString()) }
                    Log.e("# *** API ERROR *** : UserService-changePassword-notSuccessful #","" + response.message())
                }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** ERROR *** : UserService-changePassword-onFailure #","" + t.printStackTrace())
            }
        })
    }

    override fun getProfileDetails(callBack: ServiceCallBack<ProfileDetails>) {

        apiInterface?.getProfileDetails()?.enqueue(object: Callback<ProfileDetails> {

            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<ProfileDetails>, response: Response<ProfileDetails>) {
                if(response.isSuccessful) {
                    response.body()?.let { callBack.onResponse(it) }
                }
                else {
                    response.message().let { callBack.onError(it) }
                    Log.e("# *** API ERROR *** : UserService-getProfileDetails-notSuccessful #","" + response.message())
                }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<ProfileDetails>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** ERROR *** : UserService-getProfileDetails-onFailure #","" + t.printStackTrace())
            }
        })
    }

    override fun changeProfile(changeProfile: ChangeProfile, callBack: ServiceCallBack<String>) {

        apiInterface?.changeProfile(changeProfile)?.enqueue(object: Callback<String> {

            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful) {
                    response.raw().code().let { callBack.onResponse(it.toString()) }
                }
                else {
                    response.raw().code().let { callBack.onError(it.toString()) }
                    Log.e("# *** API ERROR *** : UserService-changeProfile-notSuccessful #","" + response.message())
                }
            }
            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** ERROR *** : UserService-changeProfile-onFailure #","" + t.printStackTrace())
            }
        })
    }
}
