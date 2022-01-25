package com.example.roomdao.compose.login

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomdao.R
import com.example.roomdao.compose.InputText
import com.example.roomdao.compose.RowMaterialButtons
import com.example.roomdao.compose.TitleText
import com.example.roomdao.ui.theme.RoomDaoTheme


@Composable
fun RegisterScreen(
    onCancelClick: () -> Unit,
    onRegisterClick: (List<String>) -> Unit,
    registerProgress: Boolean
) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var avatar by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var correctInput by rememberSaveable { mutableStateOf(false) }
    correctInput = validateInput(firstName, lastName, email, password)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item { TitleText(title = "Create new user", fontSize = 40.sp) }
        item {
            InputText(text = firstName, onChange = { firstName = it }, label = "First name")
            InputText(text = lastName, onChange = { lastName = it }, label = "Last name")
            InputText(text = age, onChange = { age = it }, label = "Age")
            InputText(text = avatar, onChange = { avatar = it }, label = "Avatar url")
            InputText(
                text = email,
                onChange = { email = it },
                label = "Email",
                type = KeyboardType.Email
            )
            InputText(
                text = password,
                onChange = { password = it },
                label = "Password",
                type = KeyboardType.Password
            )
        }
        item {
            RowMaterialButtons(
                actionTitle = stringResource(R.string.register),
                correctInput = correctInput,
                progress = registerProgress,
                onCancelClick = onCancelClick,
                onActionClick = {
                    onRegisterClick(listOf(firstName, lastName, age, avatar, email, password))
                }
            )
        }
    }
}

fun validateInput(name: String, lastName: String, email: String, password: String) =
    name.length >= 3 && lastName.length >= 3 &&
            Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 3

@Preview(showBackground = true, widthDp = 320)
@Composable
fun RegisterPreview() {
    RoomDaoTheme {
        RegisterScreen(
            onCancelClick = {},
            onRegisterClick = { },
            registerProgress = false
        )
    }
}
