package com.example.kotlinmvvm.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinmvvm.database.AppDatabase
import com.example.kotlinmvvm.database.entity.UserEntity
import com.example.kotlinmvvm.mapper.mapToEntity
import com.example.kotlinmvvm.networking.ApiServices
import com.example.kotlinmvvm.repository.UserRepository
import com.example.kotlinmvvm.utils.NetworkHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch

// VIEWMODEL
class UserViewModel(
    appDatabse: AppDatabase,
    apiServices: ApiServices,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    private val userRepository = UserRepository(apiServices, appDatabse.userDao())
    private val stateFlow = MutableStateFlow<Resource<List<UserEntity>>>(Resource.Loading())

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConection()) {
                userRepository.getUsers()
                    .catch {
                        stateFlow.emit(Resource.Error(it))
                    }.flatMapConcat {
                        val list = ArrayList<UserEntity>()
                        it.forEach {
                            list.add(it.mapToEntity(it))
                        }
                        userRepository.addUsers(list)
                    }
                    .collect {
                        stateFlow.emit(Resource.Success(userRepository.getDatabaseUsers()))
                    }
            } else {
                if (userRepository.getUserCount() > 0) {
                    stateFlow.emit(Resource.Success(userRepository.getDatabaseUsers()))
                } else {
                    stateFlow.emit(Resource.Error(Throwable("Internet not connection")))
                }

            }


        }
    }

    fun getStateFlow(): StateFlow<Resource<List<UserEntity>>> {
        return stateFlow
    }
}