package com.example.roomdao.compose.shopping_lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomdao.R
import com.example.roomdao.compose.InputText
import com.example.roomdao.compose.MyTopAppBar
import com.example.roomdao.compose.RowMaterialButtons
import com.example.roomdao.data.db.models.shopping_list.ShoppingList
import com.example.roomdao.data.db.models.status.StatusShoppingList
import com.example.roomdao.ui.theme.RoomDaoTheme
import java.time.Instant

@Composable
fun AddShoppingListScreen(
    createProgress: Boolean,
    onCancelClick: () -> Unit,
    onCreateClick: (ShoppingList) -> Unit
) {
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var correctInput by rememberSaveable { mutableStateOf(false) }
    correctInput = validateShoppingList(title, description)

    Scaffold(
        topBar = { MyTopAppBar(title = stringResource(R.string.create_new_shop_list), icon = {}) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            item {
                InputText(
                    text = title,
                    onChange = { title = it },
                    label = stringResource(R.string.list_name)
                )
                InputText(
                    modifier = Modifier.height(140.dp),
                    text = description,
                    onChange = { description = it },
                    label = stringResource(R.string.short_description)
                )
                InputText(
                    text = price,
                    onChange = { price = it },
                    label = stringResource(R.string.expected_price)
                )
            }
            item {
                RowMaterialButtons(
                    actionTitle = "Create",
                    correctInput = correctInput,
                    progress = createProgress,
                    onCancelClick = { onCancelClick() },
                    onActionClick = {
                        val currentTime = Instant.now()
                        onCreateClick(
                            ShoppingList(
                                id = 0L,
                                title = title,
                                description = description,
                                createAt = currentTime,
                                boughtAt = currentTime,
                                price = price.toDoubleOrNull(),
                                status = StatusShoppingList.CREATED
                            )
                        )
                    }
                )
            }
        }
    }
}

fun validateShoppingList(title: String, description: String) =
    title.length >= 3 && description.length >= 3

@Preview(showBackground = true, widthDp = 320)
@Composable
fun AddShoppingListPreview() {
    RoomDaoTheme {
        AddShoppingListScreen(
            createProgress = false,
            onCancelClick = {},
            onCreateClick = {}
        )
    }
}