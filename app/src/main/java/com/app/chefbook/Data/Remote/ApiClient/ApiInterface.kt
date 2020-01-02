package coStringm.app.chefbook.Data.Remote.ApiClient

import com.app.chefbook.Model.ServiceModel.RequestModel.ChangePassword
import com.app.chefbook.Model.ServiceModel.RequestModel.ChangeProfile
import com.app.chefbook.Model.ServiceModel.RequestModel.LoginUser
import com.app.chefbook.Model.ServiceModel.RequestModel.RegisterUser
import com.app.chefbook.Model.ServiceModel.ResponseModel.Profile
import com.app.chefbook.Model.ServiceModel.ResponseModel.ProfileDetails
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

    @POST("User/changepassword")
    fun changePassword(@Body changePassword: ChangePassword): Call<String>

    @GET("User/getprofiledetails")
    fun getProfileDetails(): Call<ProfileDetails>

    @POST("User/changeprofile")
    fun changeProfile(@Body changeProfile: ChangeProfile): Call<String>
}