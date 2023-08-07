package com.example.kotlinmvvm.mapper

import com.example.kotlinmvvm.database.entity.UserEntity
import com.example.kotlinmvvm.model.UserData

fun UserData.mapToEntity(userData: UserData): UserEntity {
    return UserEntity(userData.id ?: 0, userData.login ?: "", userData.avatarUrl ?: "")
}