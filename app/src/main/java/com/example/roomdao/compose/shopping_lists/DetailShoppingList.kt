package com.example.roomdao.compose.shopping_lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomdao.R
import com.example.roomdao.compose.MyTopAppBar
import com.example.roomdao.compose.SettingIcon
import com.example.roomdao.data.TimeFormatter
import com.example.roomdao.data.db.models.shopping_list.ShoppingList
import com.example.roomdao.data.db.models.status.StatusShoppingList
import com.example.roomdao.ui.theme.RoomDaoTheme
import java.time.Instant

@Composable
fun DetailShoppingList(shoppingList: ShoppingList, onSettingClick: (StatusShoppingList) -> Unit) {
    var openDialog by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = shoppingList.title,
                icon = { SettingIcon() },
                onIconClick = { openDialog = true }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {
            item {
                TextWithTitle(
                    title = stringResource(R.string.date_of_creation),
                    text = TimeFormatter.format(shoppingList.createAt)
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextWithTitle(
                    stringResource(R.string.description_two_dots),
                    shoppingList.description
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextWithTitle(
                    title = stringResource(R.string.expected_amount),
                    text = if (shoppingList.price != null) {
                        shoppingList.price.toString() + " ₽"
                    } else stringResource(R.string.not_limited)
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextWithTitle(
                    title = stringResource(R.string.status),
                    text = shoppingList.status.name
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(R.string.products_two_dots),
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Medium
                )
            }
        }
        if (openDialog) {
            SetStatusDialog(
                confirmCallback = { status ->
                    onSettingClick(status)
                    openDialog = false
                },
                cancelCallback = { openDialog = false }
            )
        }
    }
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
            onSettingClick = {}
        )
    }
}