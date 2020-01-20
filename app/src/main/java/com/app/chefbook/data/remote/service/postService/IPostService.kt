package com.app.chefbook.data.remote.service.postService

import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.model.serviceModel.requestModel.AddPost
import com.app.chefbook.model.serviceModel.requestModel.UserPostRate
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IPostService {
    fun sendPost(title: String, description: String, steps: Array<String>, ingredients: Array<String>, photos: Array<MultipartBody.Part>, callBack: ServiceCallBack<String>)
    fun sendLike(userId: String, callBack: ServiceCallBack<String>)
    fun sendPostRate(postRate: UserPostRate, callBack: ServiceCallBack<String>)
}