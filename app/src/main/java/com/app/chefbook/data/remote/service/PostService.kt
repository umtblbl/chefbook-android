package com.app.chefbook.data.remote.service

import android.annotation.SuppressLint
import android.util.Log
import coStringm.app.chefbook.Data.Remote.ApiClient.ApiInterface
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.serviceInterface.IPostService
import com.app.chefbook.model.serviceModel.requestModel.AddPost
import com.app.denemeinstagramapp.Data.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostService @Inject constructor() : IPostService  {

    private var apiInterface: ApiInterface? = null

    init { apiInterface = ApiClient.client?.create(ApiInterface::class.java) }

    override fun sendPost(addPost: AddPost, callBack: ServiceCallBack<String>) {
        apiInterface?.sendPost(addPost)?.enqueue(object: Callback<String> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful) {
                    response.raw().code().let { callBack.onResponse(it.toString()) }
                    Log.e("API", response.raw().code().toString())
                }
                else {
                    response.raw().code().let { callBack.onError(it.toString()) }
                    Log.e("# *** API ERROR *** : PostService-sendPost-isSuccessfull:false ||| ","${response.message()} ||| Code: ${response.raw().code()}" )
                }
            }
            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { callBack.onError(it) }
                Log.e("# *** API FAILURE *** : PostService-sendPost-onFailure #","" + t.printStackTrace())
            }
        })
    }
}