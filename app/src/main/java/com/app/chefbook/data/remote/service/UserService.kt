package com.app.chefbook.data.remote.service

import android.annotation.SuppressLint
import android.util.Log
import com.app.chefbook.data.remote.serviceInterface.IUserService
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.apiClient.ApiClient
import com.app.chefbook.data.remote.apiClient.ApiInterface
import com.app.chefbook.model.serviceModel.requestModel.ChangePassword
import com.app.chefbook.model.serviceModel.requestModel.ChangeProfile
import com.app.chefbook.model.serviceModel.requestModel.LoginUser
import com.app.chefbook.model.serviceModel.requestModel.RegisterUser
import com.app.chefbook.model.serviceModel.responseModel.Profile
import com.app.chefbook.model.serviceModel.responseModel.ProfileDetails
import okhttp3.MultipartBody
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
                    Log.e("# *** API FAILURE *** : UserService-registerUser-notSuccessful #","" + response.message())
                }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** API FAILURE *** : UserService-registerUser-onFailure #", "" + t.printStackTrace())
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
                Log.e("# *** API FAILURE *** : UserService-loginUser-onFailure #", "" + t.printStackTrace())
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
                Log.e("# *** API FAILURE *** : ProfileService-getProfile-onFailure #","" + t.printStackTrace())
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
                Log.e("# *** API FAILURE *** : UserService-changePassword-onFailure #","" + t.printStackTrace())
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
                Log.e("# *** API FAILURE *** : UserService-getProfileDetails-onFailure #","" + t.printStackTrace())
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
                Log.e("# *** API FAILURE *** : UserService-changeProfile-onFailure #","" + t.printStackTrace())
            }
        })
    }

    override fun uploadProfilePicture(profilePicture: MultipartBody.Part, callBack: ServiceCallBack<String>) {

        apiInterface?.uploadProfilePicture(profilePicture)?.enqueue(object: Callback<String> {

            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful) {
                    response.raw().code().let { callBack.onResponse(it.toString()) }
                    Log.e("API", response.raw().code().toString())
                }
                else {
                    response.raw().code().let { callBack.onError(it.toString()) }
                    Log.e("# *** API ERROR *** : UserService-uploadProfilePicture-isSuccessfull:false ||| ","${response.message()} ||| Code: ${response.raw().code()}" )
                }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** API FAILURE *** : UserService-uploadProfilePicture-onFailure #","" + t.printStackTrace())
            }
        })
    }

    override fun uploadCoverPicture(coverPicture: MultipartBody.Part, callBack: ServiceCallBack<String>) {

        apiInterface?.uploadCoverPicture(coverPicture)?.enqueue(object: Callback<String> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful) {
                    response.raw().code().let { callBack.onResponse(it.toString()) }
                    Log.e("API", response.raw().code().toString())
                }
                else {
                    response.raw().code().let { callBack.onError(it.toString()) }
                    Log.e("# *** API ERROR *** : UserService-uploadCoverPicture-isSuccessfull:false ||| ","${response.message()} ||| Code: ${response.raw().code()}" )
                }
            }
            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** API FAILURE *** : UserService-uploadCoverPicture-onFailure #","" + t.printStackTrace())
            }
        })
    }
}
