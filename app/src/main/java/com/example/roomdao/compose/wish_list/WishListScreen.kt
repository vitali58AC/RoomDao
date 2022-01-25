package com.example.roomdao.compose.wish_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.roomdao.R
import com.example.roomdao.compose.MyTopAppBar
import com.example.roomdao.compose.products.ProductItem
import com.example.roomdao.compose.shopping_lists.CenterScreenMessage
import com.example.roomdao.data.db.models.product.Products

@Composable
fun WishListScreen(productsList: List<Products>, onDeleteFromWishListClick: (Long) -> Unit) {
    Scaffold(topBar = { MyTopAppBar(title = "My wish list", icon = {}) }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(items = productsList) { product ->
                ProductItem(
                    product = product,
                    isFavorite = true,
                    onAddToWishListClick = { onDeleteFromWishListClick(product.productId) }
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (productsList.isEmpty()) CenterScreenMessage(message = stringResource(R.string.products_can_be_here))

        }
    }
}