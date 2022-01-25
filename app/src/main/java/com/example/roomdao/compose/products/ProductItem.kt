package com.example.roomdao.compose.products

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomdao.compose.FavoriteIcon
import com.example.roomdao.compose.FavoriteOutlinedIcon
import com.example.roomdao.data.db.models.product.Products
import com.example.roomdao.ui.theme.RoomDaoTheme

@Composable
fun ProductItem(product: Products, isFavorite: Boolean, onAddToWishListClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(8.dp)
            ),
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
                Text(
                    text = product.productName,
                )
                Divider(
                    Modifier
                        .fillMaxWidth(0.8f)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    color = MaterialTheme.colors.primary
                )
                Text(
                    text = product.description,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 6.dp)
            ) {
                IconButton(onClick = { onAddToWishListClick() }) {
                    if (isFavorite) FavoriteIcon(Color.Red) else FavoriteOutlinedIcon()
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun ProductItemPreview() {
    RoomDaoTheme {
        ProductItem(
            product = Products(
                productId = 100L,
                productName = "Potato",
                description = "1 kg",
                image = null
            ),
            isFavorite = true,
            onAddToWishListClick = {}
        )
    }
}
