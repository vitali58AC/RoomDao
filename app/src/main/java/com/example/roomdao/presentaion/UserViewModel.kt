package com.example.roomdao.presentaion

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao.data.db.models.product.Products
import com.example.roomdao.data.db.models.user.User
import com.example.roomdao.data.db.models.user.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    val registerProgress = mutableStateOf(false)
    val productList = mutableStateListOf<Products>()

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

    operator fun <T> List<T>.component6(): T = get(5)

    fun createUserAndProfile(args: List<String>, callback: (Long) -> Unit) {
        registerProgress.value = true
        viewModelScope.launch {
            try {
                val (name, lastName, age, avatar, email, password) = args
                val urlAvatar = if (avatar.isEmpty()) null else avatar
                repository.saveUserAndProfile(User(0, email, password), name, lastName, age, urlAvatar) {
                    callback(it)
                }
            } catch (t: Throwable) {
                Log.e("viewModel", "error with user's args ${t.message}")
            } finally {
                registerProgress.value = false
            }
        }
    }

    fun getWishListProducts(userId: Long) {
        viewModelScope.launch {
            try {
                repository.getUserWithProducts(userId).collect {
                    productList.clear()
                    productList.addAll(it[0].products)
                }
            } catch (t: Throwable) {
                Log.e("viewModel", "error with get wish list ${t.message}")
            }
        }
    }

    fun saveUserProducts(userId: Long, productId: Long) {
        viewModelScope.launch {
            try {
                repository.saveUserWithProductsCrossRef(userId, productId)
                //getWishListProducts(userId)
            } catch (t: Throwable) {
                Log.e("viewModel", "error with save user product wish list ${t.message}")
            }
        }
    }

    fun deleteUserProducts(userId: Long, productId: Long) {
        viewModelScope.launch {
            try {
                repository.deleteUserWithProductsCrossRef(userId, productId)
                //getWishListProducts(userId)
            } catch (t: Throwable) {
                Log.e("viewModel", "error with delete user product wish list ${t.message}")
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