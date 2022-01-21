package com.example.roomdao.presentaion

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao.data.db.models.user.User
import com.example.roomdao.data.db.models.user.UserProfile
import com.example.roomdao.data.db.models.user.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    val userList = mutableStateListOf<User>()
    val registerProgress = mutableStateOf(false)

    fun getUserByLoginAndPass(login: String, pass: String, userExistsCallback: (User?) -> Unit) {
        viewModelScope.launch {
            try {
                val user = repository.getUserByLoginAndPass(login, pass)
                userExistsCallback(user)
            } catch (t: Throwable) {
                Log.e("viewModel", "error with get user by login and pass ${t.message}")
            }
        }
    }

    fun getAllUsers() {
        viewModelScope.launch {
            try {
                userList.addAll(repository.getAllUsers())
            } catch (t: Throwable) {
                Log.e("viewModel", "error ${t.message}")
            }
        }
    }

    fun checkEmail(email: String, userExistsCallback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val user = repository.getUserByEmail(email)
                userExistsCallback(user != null)
            } catch (t: Throwable) {
                Log.e("viewModel", "error with check user's email ${t.message}")
            }
        }
    }

    fun createUserAndProfile(args: List<String>, callback: (Long) -> Unit) {
        registerProgress.value = true
        viewModelScope.launch {
            try {
                val (name, lastName, avatar, email, password) = args
                val urlAvatar = if (avatar.isEmpty()) null else avatar
                repository.saveUserAndProfile(User(0, email, password), name, lastName, urlAvatar) {
                    callback(it)
                }
            } catch (t: Throwable) {
                Log.e("viewModel", "error with user's args ${t.message}")
            } finally {
                registerProgress.value = false
            }
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch {
            try {
                repository.saveUsers(user)
            } catch (t: Throwable) {
                Log.e("viewModel", "error with save user ${t.message}")
            }
        }
    }

    fun saveUserProfile(userProfile: UserProfile) {
        viewModelScope.launch {
            try {
                repository.saveUserProfile(userProfile)
            } catch (t: Throwable) {
                Log.e("viewModel", "error with save user profile ${t.message}")
            }
        }
    }

    fun removeUser(user: User) {
        viewModelScope.launch {
            try {
                repository.removeUser(user.userId)
            } catch (t: Throwable) {
                Log.e("viewModel", "error with delete user ${t.message}")
            }
        }
    }

}