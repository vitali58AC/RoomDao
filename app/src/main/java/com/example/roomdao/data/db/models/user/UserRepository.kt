package com.example.roomdao.data.db.models.user

import android.util.Patterns
import androidx.room.withTransaction
import com.example.roomdao.data.db.Database
import com.example.roomdao.data.db.models.wish_list.UserProductsCrossRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class UserRepository {

    private val userDao = Database.instance.userDao()

    suspend fun getUserByLoginAndPass(login: String, pass: String) =
        userDao.getUserByLoginAndPass(login, pass)


    suspend fun saveUsers(user: User) {
        if (isUserValid(user).not()) throw Exception("Incorrect user in save method")
        userDao.insertUser(listOf(user))
    }

    suspend fun saveUserAndProfile(
        user: User,
        name: String,
        lastName: String,
        age: String,
        avatar: String?,
        callback: (Long) -> Unit
    ) {
        //Ошибка тут
        //Database.instance.withTransaction {
            val userId = userDao.insertUser(listOf(user))
            userDao.insertUserProfile(
                listOf(
                    UserProfile(
                        0,
                        name,
                        lastName,
                        avatar,
                        userId[0],
                        age.toIntOrNull() ?: 0
                    )
                )
            )
            callback(userId[0])
        //}
    }

    suspend fun saveUserProfile(userProfile: UserProfile) {
        userDao.insertUserProfile(listOf(userProfile))
    }

    suspend fun getAllUsers() = userDao.getAllUsers()

    suspend fun removeUser(userId: Long) = userDao.removeUserByID(userId = userId)

    suspend fun getUserById(userId: Long) = userDao.getUserById(userId)

    suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)

    suspend fun updateUser(user: User) {
        if (isUserValid(user).not()) throw Exception("Incorrect user in save method")
        userDao.updateUser(user)
    }

    fun getUserWithProducts(userId: Long) = userDao.getUserWishListProducts(userId)

    suspend fun saveUserWithProductsCrossRef(userId: Long, productId: Long) =
        userDao.insertUserWishListProducts(UserProductsCrossRef(userId, productId))

    suspend fun deleteUserWithProductsCrossRef(userId: Long, productId: Long) =
        userDao.deleteUserWishListProducts(userId, productId)

    private fun isUserValid(user: User): Boolean {
        return user.passwordHash.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
            user.email
        ).matches()
    }

}