package com.example.kotlinmvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvm.database.AppDatabase
import com.example.kotlinmvvm.databinding.ActivityMainBinding
import com.example.kotlinmvvm.networking.ApiClient
import com.example.kotlinmvvm.utils.NetworkHelper
import com.example.kotlinmvvm.vm.Resource
import com.example.kotlinmvvm.vm.UserViewModel
import com.example.kotlinmvvm.vm.UserViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

//view
class MainActivity : AppCompatActivity(), CoroutineScope {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: UserViewModel
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this, UserViewModelFactory(
                AppDatabase.getInstance(this), ApiClient.apiService,
                NetworkHelper(this)
            )
        )[UserViewModel::class.java]

        launch {
            viewModel.getStateFlow().collect {
                when (it) {
                    is Resource.Error -> {
                        Log.d(TAG, "onCreate: ${it.e.message}")
                    }

                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        Log.d(TAG, "onCreate: ${it.data}")
                    }
                }
            }
        }


    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}