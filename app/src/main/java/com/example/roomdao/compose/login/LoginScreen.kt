package com.example.roomdao.compose.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomdao.R
import com.example.roomdao.data.Fonts
import com.example.roomdao.ui.theme.Blue
import com.example.roomdao.ui.theme.RoomDaoTheme

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "RoomDao",
            fontWeight = FontWeight.Black,
            fontSize = 45.sp,
            fontFamily = Fonts.robotoFamily,
            modifier = Modifier.padding(top = 60.dp)
        )
        LoginPart()
        Text(
            text = "Skip ->",
            modifier = Modifier.clickable { /*TODO*/ },
            color = MaterialTheme.colors.onPrimary
        )
    }

}

@Composable
fun ColumnScope.LoginPart() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.weight(1f)
    ) {
        Text(
            text = stringResource(R.string.login_or),
            fontSize = 13.sp,
            color = MaterialTheme.colors.onPrimary
        )
        InputText(label = stringResource(R.string.login))
        InputText(label = stringResource(R.string.password))
        Row {
            MaterialButton(label = "Register")
            MaterialButton(label = "Login")
        }
    }
}

@Composable
fun MaterialButton(label: String) {
    Button(
        onClick = { /*TODO*/ },
        elevation = ButtonDefaults.elevatedButtonElevation(6.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = label,
            color = Color.White
        )
    }
}

@Composable
fun InputText(label: String) {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.primary,
            textColor = MaterialTheme.colors.onPrimary
        ),
        modifier = Modifier.padding(8.dp),
        label = { Text(label, color = Blue) },
        shape = RoundedCornerShape(8.dp)
    )
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    RoomDaoTheme {
        LoginScreen()
    }
}
