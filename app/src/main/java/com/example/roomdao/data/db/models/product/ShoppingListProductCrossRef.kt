package com.example.roomdao.data.db.models.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.roomdao.data.db.models.shopping_list.ShoppingListContract

@Entity(primaryKeys = [ShoppingListContract.Columns.SHOPPING_LIST_ID, ProductContract.Columns.PRODUCT_ID])
data class ShoppingListProductCrossRef(
    @ColumnInfo(name = ShoppingListContract.Columns.SHOPPING_LIST_ID)
    val shoppingListId: Long,
    @ColumnInfo(name = ProductContract.Columns.PRODUCT_ID)
    val productId: Long
)
