package com.app.chefbook.data.remote

interface ServiceCallBack<T> {

    fun onResponse(response: T)
    fun onError(message: String)

}