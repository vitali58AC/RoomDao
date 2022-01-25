package com.example.roomdao.presentaion

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao.data.db.models.product.Products
import com.example.roomdao.data.db.models.product.ProductsRepository
import kotlinx.coroutines.launch

class ProductsViewModel: ViewModel() {

    private val repository = ProductsRepository()
    val productList = mutableStateListOf<Products>()

    fun saveProduct(product: Products, shoppingListId: Long) {
        viewModelScope.launch {
            try {
                repository.saveProduct(product, shoppingListId)
                getProductList(shoppingListId)
            } catch (t: Throwable) {
                Log.e("product_view_model", "error with save new product ${t.message}")
            }
        }
    }

    fun getProductList(shoppingListId: Long) {
        viewModelScope.launch {
            try {
                val newProductsList = repository.getShoppingListWithProducts(shoppingListId)
                productList.clear()
                productList.addAll(newProductsList[0].products)
            } catch (t: Throwable) {
                Log.e("product_view_model", "error with save new product ${t.message}")
            }
        }

    }
}