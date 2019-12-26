package com.app.chefbook.Data.Remote

import com.app.chefbook.Model.ServiceModel.RequestModel.RegisterUser
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    //https://chefbookapi20191214013844.azurewebsites.net/api/User/Login
    @POST("User/register")
    fun registerUser(@Body registerUser: RegisterUser): Call<String>

}