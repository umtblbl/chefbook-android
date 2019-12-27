package com.app.denemeinstagramapp.Data.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    //https://chefbookapi.azurewebsites.net/
    val BASE_URL = "https://chefbookapi.azurewebsites.net/api/"
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
