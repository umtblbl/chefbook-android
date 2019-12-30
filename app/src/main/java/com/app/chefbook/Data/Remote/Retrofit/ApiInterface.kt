package com.app.chefbook.Data.Remote.Retrofit

import com.app.chefbook.Model.ServiceModel.RequestModel.LoginUser
import com.app.chefbook.Model.ServiceModel.RequestModel.RegisterUser
import com.app.chefbook.Model.ServiceModel.ResponseModel.Profile
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    //https://chefbookapi20191214013844.azurewebsites.net/api/User/Login
    @POST("User/register")
    fun registerUser(@Body registerUser: RegisterUser): Call<String>

    @POST("User/login")
    fun loginUser(@Body loginUser: LoginUser): Call<String>

    @GET("User/profile")
    fun getProfile(): Call<Profile>
}