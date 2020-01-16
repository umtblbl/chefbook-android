package com.app.chefbook.data.remote.service

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.apiClient.ApiClient
import com.app.chefbook.data.remote.apiClient.ApiInterface
import com.app.chefbook.data.remote.serviceInterface.IPostService
import com.app.chefbook.model.serviceModel.requestModel.AddPost
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostService @Inject constructor() : IPostService  {

    private var apiInterface: ApiInterface? = null

    init { apiInterface = ApiClient.client?.create(ApiInterface::class.java) }

    override fun sendPost(title: String, description: String, steps: Array<String>, ingredients: Array<String>, photos: Array<MultipartBody.Part>, callBack: ServiceCallBack<String>) {
        Log.e("API-sendPost", "API-sendPost")
        apiInterface?.sendPost(title, description, steps, ingredients, photos)?.enqueue(object: Callback<String> {
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
}