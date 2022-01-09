package com.example.roomdao.data.db.models.product

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomdao.data.db.models.shopping_list.ShoppingList
import com.example.roomdao.data.db.models.shopping_list.ShoppingListContract

data class ShoppingListWithProducts(
    @Embedded val shoppingList: ShoppingList,
    @Relation(
        parentColumn = ShoppingListContract.Columns.SHOPPING_LIST_ID,
        entityColumn = ProductContract.Columns.PRODUCT_ID,
        associateBy = Junction(ShoppingListProductCrossRef::class)
    )
    val products: List<Products>
)
