package com.app.chefbook.data.remote.service.userService

import android.annotation.SuppressLint
import android.util.Log
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.apiClient.ApiClient
import com.app.chefbook.data.remote.apiClient.IUserApi
import com.app.chefbook.model.serviceModel.requestModel.ChangePassword
import com.app.chefbook.model.serviceModel.requestModel.ChangeProfile
import com.app.chefbook.model.serviceModel.requestModel.LoginUser
import com.app.chefbook.model.serviceModel.requestModel.RegisterUser
import com.app.chefbook.model.serviceModel.responseModel.Profile
import com.app.chefbook.model.serviceModel.responseModel.ProfileDetails
import com.app.chefbook.model.serviceModel.responseModel.SearchResult
import com.app.chefbook.model.serviceModel.responseModel.UserFlowPost
import okhttp3.MultipartBody
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response


class UserService @Inject constructor() :IUserService {

    var userApi: IUserApi? = null

    init { userApi = ApiClient.client?.create(IUserApi::class.java) }

    override fun registerUser(registerUser: RegisterUser, callBack: ServiceCallBack<String>) {

        userApi?.registerUser(registerUser)?.enqueue(object : Callback<String> {

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

        userApi?.loginUser(loginUser)?.enqueue(object : Callback<String> {

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

        userApi?.getProfile()?.enqueue(object : Callback<Profile> {

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
        userApi?.changePassword(changePassword)?.enqueue(object : Callback<String> {
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

        userApi?.getProfileDetails()?.enqueue(object: Callback<ProfileDetails> {

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

        userApi?.changeProfile(changeProfile)?.enqueue(object: Callback<String> {

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

        userApi?.uploadProfilePicture(profilePicture)?.enqueue(object: Callback<String> {

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
        userApi?.uploadCoverPicture(coverPicture)?.enqueue(object: Callback<String> {
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

    override fun getUserFlowPost(callBack: ServiceCallBack<List<UserFlowPost>>) {
        userApi?.getUserFlowPost()?.enqueue(object: Callback<List<UserFlowPost>>{
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<List<UserFlowPost>>, response: Response<List<UserFlowPost>>) {
                if (response.isSuccessful)
                    response.body()?.let { callBack.onResponse(it) }
                else {
                    response.message().let { callBack.onError(it) }
                    Log.e("UserService -> ","UserService:getUserFlowPost -> notSuccessful #" + response.message())
                }
            }
            override fun onFailure(call: Call<List<UserFlowPost>>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("UserService -> ", "UserService:getUserFlowPost -> onFailure #" + t.printStackTrace())
            }
        })
    }

    override fun getSearchResult(q: String, callBack: ServiceCallBack<List<SearchResult>>) {
       userApi?.userSearch(q)?.enqueue(object: Callback<List<SearchResult>>{
           override fun onFailure(call: Call<List<SearchResult>>, t: Throwable) {
               t.message?.let { callBack.onError(it) }
               Log.e("UserService -> ", "UserService:getSearchResult -> onFailure #" + t.printStackTrace())
           }

           override fun onResponse(call: Call<List<SearchResult>>, response: Response<List<SearchResult>>) {
               if (response.isSuccessful)
                   response.body()?.let { callBack.onResponse(it) }
               else {
                   response.message().let { callBack.onError(it) }
                   Log.e("UserService -> ","UserService:getSearchResult -> notSuccessful #" + response.message())
               }
           }
       })
    }
}
