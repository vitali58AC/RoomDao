package com.example.roomdao.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roomdao.data.db.models.shopping_list.ShoppingListContract
import com.example.roomdao.data.db.models.shopping_list.ShoppingListWithUser
import com.example.roomdao.data.db.models.shopping_list.UserWithShoppingList
import com.example.roomdao.data.db.models.user.UserContract

@Dao
interface ShoppingListDao {

    //Получение всех пользователей для списка покупок
    @Transaction
    @Query("SELECT * FROM ${ShoppingListContract.TABLE_NAME}")
    fun getShoppingListWithUser(): List<ShoppingListWithUser>


    //Получение всех списков покупок для пользователя
    @Transaction
    @Query("SELECT * FROM ${UserContract.TABLE_NAME}")
    fun getUserWithShoppingList(): List<UserWithShoppingList>
}