package com.app.chefbook.data.remote.apiClient

import com.app.chefbook.model.serviceModel.requestModel.*
import com.app.chefbook.model.serviceModel.responseModel.Profile
import com.app.chefbook.model.serviceModel.responseModel.ProfileDetails
import com.app.chefbook.model.serviceModel.responseModel.SearchResult
import com.app.chefbook.model.serviceModel.responseModel.UserFlowPost
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface IUserApi {

    @POST("User/register")
    fun registerUser(@Body registerUser: RegisterUser): Call<String>

    @POST("User/login")
    fun loginUser(@Body loginUser: LoginUser): Call<String>

    @GET("User/profile")
    fun getProfile(): Call<Profile>

    @POST("User/changepassword")
    fun changePassword(@Body changePassword: ChangePassword): Call<String>

    @GET("User/getprofiledetails")
    fun getProfileDetails(): Call<ProfileDetails>

    @POST("User/changeprofile")
    fun changeProfile(@Body changeProfile: ChangeProfile): Call<String>

    @Multipart
    @PUT("User/profileimageupdate")
    fun uploadProfilePicture(@Part profilePicture: MultipartBody.Part): Call<String>

    @Multipart
    @PUT("User/coverupdate")
    fun uploadCoverPicture(@Part coverPicture: MultipartBody.Part): Call<String>

    @GET("User/wall")
    fun getUserFlowPost(): Call<List<UserFlowPost>>

    @POST("User/Search")
    fun userSearch(@Body text: String): Call<List<SearchResult>>
}