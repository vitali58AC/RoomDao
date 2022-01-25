package com.example.roomdao.compose.shopping_lists

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.roomdao.R
import com.example.roomdao.data.db.models.product.Products
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun AddProductDialog(confirmCallback: (Products) -> Unit, cancelCallback: () -> Unit) {
    var productName by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    AlertDialog(
        shape = RoundedCornerShape(8.dp),
        onDismissRequest = { cancelCallback() },
        text = {
            Column {
                Text(
                    text = "Add new product",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.background
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextField(
                    value = productName,
                    onValueChange = { productName = it },
                    label = { Text(text = "Product name") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "Description") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val product = Products(0, productName, description, null)
                    confirmCallback(product)
                },
                enabled = productName.length >= 3
            ) { Text("Add") }
        },
        dismissButton = {
            Button(onClick = { cancelCallback() }) { Text(stringResource(R.string.cancel)) }
        }
    )
}
