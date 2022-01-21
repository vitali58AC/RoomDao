package com.example.roomdao.data.db.dao

import androidx.room.*
import com.example.roomdao.data.db.models.user.User
import com.example.roomdao.data.db.models.user.UserAndProfile
import com.example.roomdao.data.db.models.user.UserContract
import com.example.roomdao.data.db.models.user.UserProfile
import com.example.roomdao.data.db.models.wish_list.UserProfileWithProducts

@Dao
interface UserDao {

    @Query("SELECT * FROM ${UserContract.TABLE_NAME} WHERE ${UserContract.Columns.EMAIL} = :login AND ${UserContract.Columns.PASSWORD_HASH} = :pass")
    suspend fun getUserByLoginAndPass(login: String, pass: String): User?

    //В случае конфликта, user будет заменён
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userProfiles: List<UserProfile>)

    @Query("SELECT * FROM ${UserContract.TABLE_NAME}")
    suspend fun getAllUsers(): List<User>

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM ${UserContract.TABLE_NAME} WHERE ${UserContract.Columns.USER_ID} = :userId")
    suspend fun removeUserByID(userId: Long)

    @Query("SELECT * FROM ${UserContract.TABLE_NAME} WHERE ${UserContract.Columns.USER_ID} = :userId")
    suspend fun getUserById(userId: Long): User?

    @Query("SELECT * FROM ${UserContract.TABLE_NAME} WHERE ${UserContract.Columns.EMAIL} = :email")
    suspend fun getUserByEmail(email: String): User?

    @Update
    suspend fun updateUser(user: User)

    //Связь 1-1 для user и userProfile
    //Этот метод требует, чтобы Room выполнял два запроса, поэтому добавьте @Transaction
    //аннотацию к этому методу, чтобы гарантировать, что вся операция выполняется атомарно.
    @Transaction
    @Query("SELECT * FROM ${UserContract.TABLE_NAME}")
    fun getUserAndUserProfile(): List<UserAndProfile>

    @Transaction
    @Query("SELECT * FROM ${UserContract.TABLE_PROFILE}")
    fun getProductsWishListProducts(): List<UserProfileWithProducts>
}