package com.example.roomdao.data.db.dao

import androidx.room.*
import com.example.roomdao.data.db.models.product.Products
import com.example.roomdao.data.db.models.product.ShoppingListProductCrossRef
import com.example.roomdao.data.db.models.product.ShoppingListWithProducts
import com.example.roomdao.data.db.models.shopping_list.ShoppingListContract

@Dao
interface ProductDao {

    //Получение всех продуктов для списка покупок
    @Transaction
    @Query("SELECT * FROM ${ShoppingListContract.TABLE_NAME} WHERE ${ShoppingListContract.Columns.SHOPPING_LIST_ID} = :shoppingListId")
    suspend fun getShoppingListWithProducts(shoppingListId: Long): List<ShoppingListWithProducts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(products: Products): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingListProductCrossRef(crossRef: ShoppingListProductCrossRef)
}