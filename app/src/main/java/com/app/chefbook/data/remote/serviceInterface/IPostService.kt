package com.app.chefbook.data.remote.serviceInterface

import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.model.serviceModel.requestModel.AddPost
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IPostService {
    fun sendPost(title: String, description: String, steps: Array<String>, ingredients: Array<String>, photos: Array<MultipartBody.Part>, callBack: ServiceCallBack<String>)
}