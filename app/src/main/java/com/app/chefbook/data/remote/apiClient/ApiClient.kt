package com.app.chefbook.data.remote.apiClient

import com.app.chefbook.data.remote.OAuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    //https://chefbookapi.azurewebsites.net/
    private const val BASE_URL = "https://chefbookapi.azurewebsites.net/api/"
    //private const val BASE_URL = "http://10.0.2.2:5000/api/"
    private var accessToken: String = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiI0YmNiYmNiZi1kNzVmLTRjMGYtODIxYy0yYTgzM2Y4MDBmZjQiLCJ1bmlxdWVfbmFtZSI6InN0cmluZyIsIm5iZiI6MTU3Nzg4ODYyNCwiZXhwIjoxNTgwNDgwNjI0LCJpYXQiOjE1Nzc4ODg2MjR9.Qgy4y7uIw6qIC6hvzVyjbemhPIo2PZT0--B3DdbVMTBn0zVx2w0bo8Sk_I8cwNbMOx2CZkwVo-hzeba51aNzNA"

    private var retrofit: Retrofit? = null
    val client: Retrofit?
        get() {

            val client = OkHttpClient.Builder().addInterceptor(OAuthInterceptor("Bearer", accessToken)).build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
            }
            return retrofit
        }
}
