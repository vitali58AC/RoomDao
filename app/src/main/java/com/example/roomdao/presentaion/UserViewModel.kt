package com.example.roomdao.presentaion

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao.data.UserRepository
import com.example.roomdao.data.db.models.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    val userList = mutableStateListOf<User>()

    fun getAllUsers() {
        viewModelScope.launch {
            try {
                userList.addAll(repository.getAllUsers())
            } catch (t: Throwable) {
                Log.e("viewModel", "error ${t.message}")
            }
        }
    }

    fun removeUser(user: User) {
        viewModelScope.launch {
            try {
                repository.removeUser(user.id)
            } catch (t: Throwable) {
                Log.e("viewModel", "error with delete user ${t.message}")
            }
        }
    }

    //Реализовать сохранение, получение и обновление user и перейти к созданию экрана
    //На этом первый урок всё, окончен

}