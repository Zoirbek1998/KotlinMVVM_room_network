package com.example.kotlinmvvm.networking

import me.sianaki.flowretrofitadapter.FlowCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.github.com/"

    private fun getRetrofit():Retrofit{
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .build()
    }

    val apiService = getRetrofit().create(ApiServices::class.java)
}