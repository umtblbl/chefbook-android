package com.app.chefbook.data.remote.apiClient

import com.app.chefbook.model.serviceModel.requestModel.UserPostRate
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface IPostApi {

    @Multipart
    @POST("Post/addpost")
    fun sendPost(@Part("title") title: String,
                 @Part("description") description: String,
                 @Part("steps") steps: Array<String>,
                 @Part("ingredients") ingredients: Array<String>,
                 @Part photos: Array<MultipartBody.Part> ): Call<String>

    @POST("Post/like")
    fun sendLike(@Part ("postId") userId:String): Call<String>

    @POST("Post/starAdd")
    fun sendPostRate(@Body userPostRate:UserPostRate): Call<String>

}