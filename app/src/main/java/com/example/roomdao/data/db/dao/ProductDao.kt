package com.example.roomdao.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roomdao.data.db.models.product.ShoppingListWithProducts
import com.example.roomdao.data.db.models.shopping_list.ShoppingListContract

@Dao
interface ProductDao {

    //Получение всех продуктов для списка покупок
    @Transaction
    @Query("SELECT * FROM ${ShoppingListContract.TABLE_NAME}")
    fun getShoppingListWithProducts(): List<ShoppingListWithProducts>
}