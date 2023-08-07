package com.example.kotlinmvvm.networking

import com.example.kotlinmvvm.model.UserData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiServices {

    @GET("users")
    fun getUsers () : Flow<List<UserData>>
}