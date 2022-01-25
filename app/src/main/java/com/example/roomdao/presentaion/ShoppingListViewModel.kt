package com.example.roomdao.presentaion

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao.data.db.models.shopping_list.ShoppingList
import com.example.roomdao.data.db.models.shopping_list.ShoppingListRepository
import com.example.roomdao.data.db.models.status.StatusShoppingList
import kotlinx.coroutines.launch

class ShoppingListViewModel : ViewModel() {

    private val repository = ShoppingListRepository()

    var shoppingLists = mutableStateListOf<ShoppingList>()
    val createProgress = mutableStateOf(false)

    fun getUserShopList(userId: Long) {
        viewModelScope.launch {
            try {
                val shoppingList = repository.getUserShoppingList(userId).shoppingLists
                shoppingLists = mutableStateListOf()
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

    fun updateShoppingList(id: Long, statusShoppingList: StatusShoppingList) {
        viewModelScope.launch {
            try {
                val oldShoppingList = getCurrentShoppingList(id)
                val updatedShoppingList = oldShoppingList.copy(status = statusShoppingList)
                repository.updateShoppingList(updatedShoppingList)
                shoppingLists.remove(oldShoppingList)
                shoppingLists.add(updatedShoppingList)

            } catch (t: Throwable) {
                Log.e("shop_list_viewModel", "error with update shop list ${t.message}")
            }
        }
    }

}