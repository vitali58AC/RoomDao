package com.example.roomdao.compose.shopping_lists

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.roomdao.R
import com.example.roomdao.data.db.models.status.StatusShoppingList

@Composable
fun SetStatusDialog(confirmCallback: (StatusShoppingList) -> Unit, cancelCallback: () -> Unit) {
    val radioOptions = listOf("Created", "Active", "Bought", "Closed")
    val (selectedOption, onOptionSelected) = rememberSaveable { mutableStateOf(radioOptions[0]) }
    AlertDialog(
        shape = RoundedCornerShape(8.dp),
        onDismissRequest = { cancelCallback() },
        text = {
            Column {
                Text(
                    text = stringResource(R.string.set_list_status),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.background
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(Modifier.selectableGroup()) {
                    radioOptions.forEach { text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = { onOptionSelected(text) },
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = null // null recommended for accessibility with screen readers
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.body1.merge(),
                                modifier = Modifier.padding(start = 16.dp),
                                color = MaterialTheme.colors.background
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                val chosen = when (selectedOption) {
                    "Active" -> StatusShoppingList.ACTIVE
                    "Bought" -> StatusShoppingList.BOUGHT
                    "Closed" -> StatusShoppingList.CLOSED
                    else -> StatusShoppingList.CREATED
                }
                confirmCallback(chosen)
            }) { Text(stringResource(R.string.set_status)) }
        },
        dismissButton = {
            Button(onClick = { cancelCallback() }) { Text(stringResource(R.string.cancel)) }
        }
    )
}
