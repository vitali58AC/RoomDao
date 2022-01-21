package com.example.roomdao.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.roomdao.ui.theme.Blue

@Composable
fun InputText(
    modifier: Modifier = Modifier,
    text: String,
    onChange: (String) -> Unit,
    label: String,
    type: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = text,
        onValueChange = { onChange(it) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.primary,
            textColor = MaterialTheme.colors.onPrimary
        ),
        modifier = modifier.padding(8.dp),
        label = { Text(label, color = Blue) },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = type),
        visualTransformation = visualTransformation
    )
}