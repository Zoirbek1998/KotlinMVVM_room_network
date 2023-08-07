package com.example.kotlinmvvm.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinmvvm.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    fun addUser(userEntity: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsers(list: List<UserEntity>)

    @Query("select*from userentity")
    fun getUser(): List<UserEntity>

    @Query("select count(*) from userentity")
    fun getUserCount() : Int


}