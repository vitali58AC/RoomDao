package com.example.roomdao.compose.login

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomdao.R
import com.example.roomdao.compose.InputText
import com.example.roomdao.compose.MaterialButton
import com.example.roomdao.compose.TitleText
import com.example.roomdao.ui.theme.RoomDaoTheme

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: (String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText(title = "RoomDao", fontSize = 45.sp)
        LoginPart(onRegisterClick, onLoginClick)
    }

}

@Composable
fun ColumnScope.LoginPart(onRegisterClick: () -> Unit, onLoginClick: (String, String) -> Unit) {
    var login by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    var correctInput by rememberSaveable { mutableStateOf(false) }
    correctInput = validateLoginInput(login, pass)
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
        InputText(text = login, onChange = { login = it }, label = stringResource(R.string.email))
        InputText(
            text = pass,
            onChange = { pass = it },
            label = stringResource(R.string.password),
            visualTransformation = PasswordVisualTransformation()
        )
        Row {
            MaterialButton(label = stringResource(R.string.register), onClick = onRegisterClick)
            MaterialButton(
                label = stringResource(R.string.login),
                enable = correctInput,
                onClick = {
                    onLoginClick(login, pass)
                })
        }
    }
}

fun validateLoginInput(login: String, pass: String) =
    Patterns.EMAIL_ADDRESS.matcher(login).matches() && pass.length >= 3

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    RoomDaoTheme {
        LoginScreen({ }) { _, _ -> }
    }
}
