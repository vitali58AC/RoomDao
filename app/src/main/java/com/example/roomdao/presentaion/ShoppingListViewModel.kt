package com.example.roomdao.presentaion

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao.data.db.models.shopping_list.ShoppingList
import com.example.roomdao.data.db.models.shopping_list.ShoppingListRepository
import kotlinx.coroutines.launch

class ShoppingListViewModel : ViewModel() {

    private val repository = ShoppingListRepository()

    var shoppingLists = mutableStateListOf<ShoppingList>()
    val createProgress = mutableStateOf(false)
    var currentShoppingList: MutableState<ShoppingList?> = mutableStateOf(null)


    fun getUserShopList(userId: Long) {
        shoppingLists = mutableStateListOf()
        viewModelScope.launch {
            try {
                val shoppingList = repository.getUserShoppingList(userId).shoppingLists
                shoppingLists.addAll(shoppingList)
            } catch (t: Throwable) {
                Log.e("shop_list_viewModel", "error with get user's shop list ${t.message}")
            }
        }
    }

    fun insertShoppingList(userId: Long, list: ShoppingList) {
        createProgress.value = true
        viewModelScope.launch {
            try {
                repository.saveShoppingList(userId, list)
                getUserShopList(userId)
            } catch (t: Throwable) {
                Log.e("shop_list_viewModel", "error with add shop list ${t.message}")
            } finally {
                createProgress.value = false
            }
        }
    }


    fun getCurrentShoppingList(shoppingListId: Long): ShoppingList {
        return shoppingLists.first { it.id == shoppingListId }
    }

}