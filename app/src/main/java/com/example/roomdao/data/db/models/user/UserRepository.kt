package com.example.roomdao.data.db.models.user

import android.util.Patterns
import com.example.roomdao.data.db.Database
import kotlinx.coroutines.delay

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
        avatar: String?,
        callback: (Long) -> Unit
    ) {
        userDao.insertUser(listOf(user))
        //Это что б room успел создать User таблицу
        delay(1000L)
        val currentUser = userDao.getUserByEmail(user.email)
        if (currentUser != null) {
            userDao.insertUserProfile(
                listOf(
                    UserProfile(
                        0,
                        name,
                        lastName,
                        avatar,
                        currentUser.userId
                    )
                )
            )
            callback(currentUser.userId)
        }
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

    private fun isUserValid(user: User): Boolean {
        return user.passwordHash.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
            user.email
        ).matches()
    }

}