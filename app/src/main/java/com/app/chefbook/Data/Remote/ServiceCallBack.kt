package com.app.chefbook.Data.Remote

interface ServiceCallBack<T> {

    fun onResponse(response: T)
    fun onError(message: String)

}