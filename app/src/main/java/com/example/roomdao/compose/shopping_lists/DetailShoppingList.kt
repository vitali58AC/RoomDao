package com.example.roomdao.compose.shopping_lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomdao.R
import com.example.roomdao.compose.MyTopAppBar
import com.example.roomdao.compose.PlusIcon
import com.example.roomdao.compose.SettingIcon
import com.example.roomdao.compose.products.ProductItem
import com.example.roomdao.data.TimeFormatter
import com.example.roomdao.data.db.models.product.Products
import com.example.roomdao.data.db.models.shopping_list.ShoppingList
import com.example.roomdao.data.db.models.status.StatusShoppingList
import com.example.roomdao.ui.theme.RoomDaoTheme
import java.time.Instant

@Composable
fun DetailShoppingList(
    shoppingList: ShoppingList,
    productsList: List<Products>,
    wishListProducts: List<Products>,
    onSettingClick: (StatusShoppingList) -> Unit,
    onAddProductClick: (Products) -> Unit,
    onAddToWishListClick: (Long) -> Unit,
    onDeleteFromWishListClick: (Long) -> Unit
) {
    var openSettingDialog by rememberSaveable { mutableStateOf(false) }
    var openAddProductDialog by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = shoppingList.title,
                icon = { SettingIcon() },
                onIconClick = { openSettingDialog = true }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {
            item {
                DetailInfoBlock(
                    instant = shoppingList.createAt,
                    description = shoppingList.description,
                    price = shoppingList.price,
                    status = shoppingList.status.name
                )
            }
            item {
                MaterialTextButton(onClick = { openAddProductDialog = true })
            }
            items(items = productsList) { product ->
                ProductItem(
                    product = product,
                    isFavorite = product in wishListProducts
                ) {
                    if (product in wishListProducts) {
                        onDeleteFromWishListClick(product.productId)
                    } else {
                        onAddToWishListClick(product.productId)
                    }
                }
            }
        }
        if (openSettingDialog) {
            SetStatusDialog(
                confirmCallback = { status ->
                    onSettingClick(status)
                    openSettingDialog = false
                },
                cancelCallback = { openSettingDialog = false }
            )
        }
        if (openAddProductDialog) {
            AddProductDialog(
                confirmCallback = { newProduct ->
                    onAddProductClick(newProduct)
                    openAddProductDialog = false
                },
                cancelCallback = { openAddProductDialog = false }
            )
        }
    }
}

@Composable
fun MaterialTextButton(onClick: () -> Unit) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = { onClick() }) {
            PlusIcon()
            Text(
                text = "Add product",
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.surface
            )
        }
    }
}

@Composable
fun DetailInfoBlock(instant: Instant, description: String, price: Double?, status: String) {
    TextWithTitle(
        title = stringResource(R.string.date_of_creation),
        text = TimeFormatter.format(instant)
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextWithTitle(stringResource(R.string.description_two_dots), description)
    Spacer(modifier = Modifier.height(15.dp))
    TextWithTitle(
        title = stringResource(R.string.expected_amount),
        text = if (price != null) {
            "$price ₽"
        } else stringResource(R.string.not_limited)
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextWithTitle(title = stringResource(R.string.status), text = status)
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = stringResource(R.string.products_two_dots),
        modifier = Modifier.padding(start = 8.dp),
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun TextWithTitle(title: String, text: String) {
    Text(text = title, modifier = Modifier.padding(start = 8.dp))
    Text(
        text = text,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.primary)
            .padding(4.dp)
            .fillMaxWidth(),
    )
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DetailShoppingListPreview() {
    RoomDaoTheme {
        DetailShoppingList(
            shoppingList = ShoppingList(
                id = 100,
                title = "Example title",
                description = "Example long description about this shopping list",
                createAt = Instant.now(),
                boughtAt = null,
                price = 100.0,
                status = StatusShoppingList.ACTIVE
            ),
            productsList = listOf(
                Products(
                    productId = 100L,
                    productName = "Potato",
                    description = "1 kg",
                    image = null
                ),
                Products(
                    productId = 10L,
                    productName = "Water \"Dom\"",
                    description = "1 bottle",
                    image = null
                ),
                Products(
                    productId = 1001L,
                    productName = "Wheels",
                    description = "null",
                    image = null
                )
            ),
            wishListProducts = listOf(
                Products(
                    productId = 10L,
                    productName = "Water \"Dom\"",
                    description = "1 bottle",
                    image = null
                ),
                Products(
                    productId = 1001L,
                    productName = "Wheels",
                    description = "null",
                    image = null
                )
            ),
            onSettingClick = {},
            onAddProductClick = {},
            onAddToWishListClick = {},
            onDeleteFromWishListClick = {}
        )
    }
}