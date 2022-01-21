package com.example.roomdao.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MaterialButton(label: String, enable: Boolean = true, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        elevation = ButtonDefaults.elevatedButtonElevation(6.dp),
        modifier = Modifier.padding(8.dp),
        enabled = enable
    ) {
        Text(
            text = label,
            color = Color.White
        )
    }
}