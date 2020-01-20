package com.app.chefbook.data.remote.apiClient

import com.app.chefbook.data.remote.OAuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "--------API_BASE_URL_ADRESS--------"
    private var accessToken: String = "--------ACCESS_TOKEN-------"
    //AccessToken preferencesten veya veritabanından alınacak.
    //Interceptor'a response'un 401 (unauthorization) olma kontrolü eklenip, refreshToken durumu implement edilecek.

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
