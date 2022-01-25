package com.example.roomdao.compose.shopping_lists

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomdao.R
import com.example.roomdao.compose.FavoriteIcon
import com.example.roomdao.compose.MyTopAppBar
import com.example.roomdao.data.db.models.shopping_list.ShoppingList
import com.example.roomdao.data.db.models.shopping_list.ShoppingListExample
import com.example.roomdao.ui.theme.NavyLight
import com.example.roomdao.ui.theme.RoomDaoTheme


@Composable
fun ShoppingListScreen(
    shoppingList: List<ShoppingList> = emptyList(),
    onShoppingListClick: (ShoppingList) -> Unit,
    onAddShopListClick: () -> Unit,
    onWishListClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = { FABButton(onAddShopListClick) },
        topBar = {
            MyTopAppBar(
                title = stringResource(R.string.shopping_lists),
                icon = { FavoriteIcon() },
                onIconClick = { onWishListClick() }
            )
        }
    ) {
        LazyColumn(Modifier.padding(top = 8.dp)) {
            items(items = shoppingList) {
                ShoppingListItem(item = it) { onShoppingListClick(it) }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (shoppingList.isEmpty()) CenterScreenMessage(message = stringResource(R.string.you_list_is_empty))

        }
    }
}

@Composable
fun ColumnScope.CenterScreenMessage(message: String) {
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            fontWeight = FontWeight.Light,
            fontSize = 18.sp,
            color = NavyLight
        )
    }
}

@Composable
fun FABButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_shop_list)
        )
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun ShoppingListPreview() {
    RoomDaoTheme {
        ShoppingListScreen(
            shoppingList = ShoppingListExample.list,
            onShoppingListClick = { },
            onAddShopListClick = {},
            onWishListClick = {}
        )
    }
}