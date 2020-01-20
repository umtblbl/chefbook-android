package com.app.chefbook.data.remote.service.postService

import android.annotation.SuppressLint
import android.util.Log
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.apiClient.ApiClient
import com.app.chefbook.data.remote.apiClient.IPostApi
import com.app.chefbook.model.serviceModel.requestModel.UserPostRate
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostService @Inject constructor() :
    IPostService {

    private var postApi: IPostApi? = null

    init { postApi = ApiClient.client?.create(IPostApi::class.java) }

    override fun sendPost(title: String, description: String, steps: Array<String>, ingredients: Array<String>, photos: Array<MultipartBody.Part>, callBack: ServiceCallBack<String>) {
        Log.e("API", "PostService:sendPost")
        postApi?.sendPost(title, description, steps, ingredients, photos)?.enqueue(object: Callback<String> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful) {
                    Log.e("API-sendPost", "onResponse:" + response.raw().code().toString())
                    response.raw().code().let { callBack.onResponse(it.toString()) }
                }
                else {
                    Log.e("API-sendPost",": PostService-sendPost | isSuccessfully:false | " + "${response.message()} | Code: ${response.raw().code()}")
                    response.raw().code().let { callBack.onError(it.toString()) }
                }
            }
            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("API-sendPost","PostService-sendPost-onFailure | " + "Message -> ${t.message}")
                t.message?.let { callBack.onError(it) }
            }
        })
    }

    override fun sendLike(userId: String, callBack: ServiceCallBack<String>) {
        postApi?.sendLike(userId)?.enqueue(object :Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful) {
                    Log.e("API-sendLike", "onResponse:" + response.raw().code().toString())
                    response.raw().code().let { callBack.onResponse(it.toString()) }
                }
                else {
                    Log.e("API-sendLike",": PostService-sendLike | isSuccessfully:false | " + "${response.message()} | Code: ${response.raw().code()}")
                    response.raw().code().let { callBack.onError(it.toString()) }
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("API-sendLike","PostService-sendPost-onFailure | " + "Message -> ${t.message}")
                t.message?.let { callBack.onError(it) }
            }
        })
    }

    override fun sendPostRate(postRate: UserPostRate, callBack: ServiceCallBack<String>) {
        postApi?.sendPostRate(postRate)?.enqueue(object :Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful) {
                    Log.e("API-sendPostRate", "onResponse:" + response.raw().code().toString())
                    response.raw().code().let { callBack.onResponse(it.toString()) }
                }
                else {
                    Log.e("API-sendPostRate",": PostService-sendPostRate | isSuccessfully:false | " + "${response.message()} | Code: ${response.raw().code()}")
                    response.raw().code().let { callBack.onError(it.toString()) }
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("API-sendPostRate","PostService-sendPostRate-onFailure | " + "Message -> ${t.message}")
                t.message?.let { callBack.onError(it) }
            }
        })
    }
}