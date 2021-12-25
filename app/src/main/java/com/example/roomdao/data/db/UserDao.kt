package com.example.roomdao.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdao.data.db.models.User
import com.example.roomdao.data.db.models.UserContract

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(users: List<User>)

    @Query("SELECT * FROM ${UserContract.TABLE_NAME}")
    suspend fun getAllUsers(): List<User>
}