package com.example.roomdao.data.db.models.product

import androidx.room.withTransaction
import com.example.roomdao.data.db.Database

class ProductsRepository {

    private val productsDao = Database.instance.productDao()

    suspend fun saveProduct(product: Products, shoppingListId: Long) {
        Database.instance.withTransaction {
            val productId = productsDao.insertProduct(product)
            productsDao.insertShoppingListProductCrossRef(
                ShoppingListProductCrossRef(
                    shoppingListId,
                    productId
                )
            )
        }
    }

    suspend fun getShoppingListWithProducts(shoppingListId: Long) =
        productsDao.getShoppingListWithProducts(shoppingListId)
}