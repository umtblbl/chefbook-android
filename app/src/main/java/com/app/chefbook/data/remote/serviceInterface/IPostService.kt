package com.app.chefbook.data.remote.serviceInterface

import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.model.serviceModel.requestModel.AddPost

interface IPostService {
    fun sendPost(addPost: AddPost, callBack: ServiceCallBack<String>)
}