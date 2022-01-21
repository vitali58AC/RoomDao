package com.example.roomdao.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import kotlin.reflect.KFunction1

@Composable
fun SideEffect(func: KFunction1<Long, Unit>, currentId: Long) {
    val currentCall by rememberUpdatedState(func)

    LaunchedEffect(true) {
        currentCall(currentId)
    }
}
