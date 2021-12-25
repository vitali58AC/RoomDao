package com.example.roomdao.data

import android.util.Patterns
import com.example.roomdao.data.db.Database
import com.example.roomdao.data.db.models.User

class UserRepository {

    private val userDao = Database.instance.userDao()


    suspend fun saveUsers(user: User) {
        if (isUserValid(user).not()) throw Exception("Incorrect user in save method")
        userDao.insertUser(listOf(user))
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    private fun isUserValid(user: User): Boolean {
        return user.firstName.isNotBlank() && user.lastName.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
            user.email
        ).matches()
    }

}