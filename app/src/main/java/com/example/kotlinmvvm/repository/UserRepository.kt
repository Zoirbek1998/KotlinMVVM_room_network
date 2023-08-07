package com.example.kotlinmvvm.repository

import com.example.kotlinmvvm.database.dao.UserDao
import com.example.kotlinmvvm.database.entity.UserEntity
import com.example.kotlinmvvm.networking.ApiServices
import kotlinx.coroutines.flow.flow

//MODEL
class UserRepository(private val apiServices: ApiServices, private val userDao: UserDao) {

    fun getUsers() = apiServices.getUsers()

    fun addUsers(list: List<UserEntity>) = flow { emit(userDao.addUsers(list)) }

    fun getDatabaseUsers() = userDao.getUser()

    fun getUserCount() = userDao.getUserCount()
}