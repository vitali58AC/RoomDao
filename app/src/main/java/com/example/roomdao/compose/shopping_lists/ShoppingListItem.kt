package com.example.roomdao.compose.shopping_lists

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomdao.data.db.models.shopping_list.ShoppingList
import com.example.roomdao.data.db.models.status.StatusShoppingList
import com.example.roomdao.ui.theme.Navy
import com.example.roomdao.ui.theme.NavyGray
import com.example.roomdao.ui.theme.NormalBlue
import com.example.roomdao.ui.theme.Teal400

@Composable
fun ShoppingListItem(item: ShoppingList, onShoppingListClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .border(
                width = 1.dp,
                color = chooseStatusColor(item.status),
                shape = RoundedCornerShape(8.dp)
            ),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(8.dp)
        ) {
        Row(
            modifier = Modifier.fillMaxSize().clickable { onShoppingListClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                fontWeight = FontWeight.Light,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = item.status.name.lowercase().replaceFirstChar { first -> first.uppercase() },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                textAlign = TextAlign.End,
                color = chooseStatusColor(item.status),
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                fontWeight = FontWeight.Light
            )
        }
    }
}

fun chooseStatusColor(status: StatusShoppingList) = when (status) {
    StatusShoppingList.CREATED -> NormalBlue
    StatusShoppingList.ACTIVE -> Navy
    StatusShoppingList.BOUGHT -> Teal400
    StatusShoppingList.CLOSED -> NavyGray
}

