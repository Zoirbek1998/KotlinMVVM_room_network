package com.example.kotlinmvvm.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvm.database.AppDatabase
import com.example.kotlinmvvm.networking.ApiServices
import com.example.kotlinmvvm.utils.NetworkHelper

class UserViewModelFactory(
    private val appDatabase: AppDatabase,
    private val apiServices: ApiServices,
    private val networkHelper: NetworkHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(appDatabase,apiServices,networkHelper) as T
        }
        return throw Exception("Error")
    }
}