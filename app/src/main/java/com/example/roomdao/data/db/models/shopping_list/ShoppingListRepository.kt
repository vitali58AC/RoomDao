package com.example.roomdao.data.db.models.shopping_list

import android.util.Log
import com.example.roomdao.data.db.Database
import java.time.Instant

class ShoppingListRepository {

    private val shoppingListDao = Database.instance.shoppingListDao()

    suspend fun getUserShoppingList(userId: Long): UserWithShoppingList {
        return shoppingListDao.getUserWithShoppingList(userId)
    }

    suspend fun saveShoppingList(userId: Long, list: ShoppingList) {
        if (isShopListValid(list).not()) throw Exception("Incorrect shopping list in save method")
        val idList = shoppingListDao.insertShoppingLists(listOf(list))
        shoppingListDao.insertUserShoppingListCrossRef(
            UserShoppingListCrossRef(idList[0], userId)
        )
    }

    private fun isShopListValid(shopList: ShoppingList) =
        shopList.title.isNotEmpty() && shopList.createAt.isAfter(
            Instant.MIN
        )
}