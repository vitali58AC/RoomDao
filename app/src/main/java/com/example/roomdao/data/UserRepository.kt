package com.example.roomdao.data

import android.util.Patterns
import com.example.roomdao.data.db.Database
import com.example.roomdao.data.db.models.user.User

class UserRepository {

    private val userDao = Database.instance.userDao()


    suspend fun saveUsers(user: User) {
        if (isUserValid(user).not()) throw Exception("Incorrect user in save method")
        userDao.insertUser(listOf(user))
    }

    suspend fun getAllUsers() = userDao.getAllUsers()

    suspend fun removeUser(userId: Long) = userDao.removeUserByID(userId = userId)

    private fun isUserValid(user: User): Boolean {
        return user.passwordHash.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
            user.email
        ).matches()
    }

    suspend fun getUserById(userId: Long) = userDao.getUserById(userId)

    suspend fun updateUser(user: User) {
        if (isUserValid(user).not()) throw Exception("Incorrect user in save method")
        userDao.updateUser(user)
    }

}