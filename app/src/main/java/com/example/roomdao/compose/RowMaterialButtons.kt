package com.example.roomdao.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

@Composable
fun RowMaterialButtons(
    actionTitle: String,
    correctInput: Boolean,
    progress: Boolean,
    onCancelClick: () -> Unit,
    onActionClick: () -> Unit
) {
    if (progress.not()) {
        Row {
            MaterialButton(label = "Cancel") { onCancelClick() }
            MaterialButton(label = actionTitle, enable = correctInput) { onActionClick() }
        }
    } else {
        ProgressIndicator(visibility = progress)
    }
}