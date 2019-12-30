package com.app.denemeinstagramapp.Data.Retrofit

import android.content.Context
import com.app.chefbook.Data.Remote.OAuthInterceptor
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.coroutineContext

object ApiClient {

    //https://chefbookapi.azurewebsites.net/
    val BASE_URL = "https://chefbookapi.azurewebsites.net/api/"
    private var retrofit: Retrofit? = null
    private var accessToken: String = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiI0YmNiYmNiZi1kNzVmLTRjMGYtODIxYy0yYTgzM2Y4MDBmZjQiLCJ1bmlxdWVfbmFtZSI6InN0cmluZyIsIm5iZiI6MTU3NzY5MDU1MiwiZXhwIjoxNTgwMjgyNTUyLCJpYXQiOjE1Nzc2OTA1NTJ9.UDxgx7KU2eiRlMEwHQZcrHS69hQ6OA-Rq9hMQz2ev8Aqf_tgeDBypRwgmNHSWCsM8FGQXUqHagtlNOB0jsVNyw"

    fun setAccessToken(accessToken: String) {
        this.accessToken = accessToken
    }

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
