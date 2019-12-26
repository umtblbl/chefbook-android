package com.app.denemeinstagramapp.Data.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    //val BASE_URL = "https://www.instagram.com/"
    val BASE_URL = "https://chefbookapi20191214013844.azurewebsites.net/api/"
    private var retrofit: Retrofit? = null

    val client:Retrofit?
        get() {
            if(retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }
            return retrofit
        }
}