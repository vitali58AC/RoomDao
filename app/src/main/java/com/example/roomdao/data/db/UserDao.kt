package com.example.roomdao.data.db

import androidx.room.*
import com.example.roomdao.data.db.models.User
import com.example.roomdao.data.db.models.UserContract

@Dao
interface UserDao {

    //В случае конфликта, user будет заменён
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: List<User>)

    @Query("SELECT * FROM ${UserContract.TABLE_NAME}")
    suspend fun getAllUsers(): List<User>

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM ${UserContract.TABLE_NAME} WHERE ${UserContract.Columns.ID} = :userId")
    suspend fun removeUserByID(userId: Long)

    @Query("SELECT * FROM ${UserContract.TABLE_NAME} WHERE ${UserContract.Columns.ID} = :userId")
    suspend fun getUserById(userId: Long): User?

    @Update
    suspend fun updateUser(user: User)
}