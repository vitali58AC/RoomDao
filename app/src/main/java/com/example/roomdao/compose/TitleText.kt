package com.example.roomdao.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomdao.data.Fonts

@Composable
fun TitleText(title: String, fontSize: TextUnit) {
    Text(
        text = title,
        fontWeight = FontWeight.Black,
        fontSize = fontSize,
        fontFamily = Fonts.robotoFamily,
        modifier = Modifier.padding(top = 60.dp),
        color = MaterialTheme.colors.onPrimary
    )
}