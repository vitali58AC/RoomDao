package com.example.roomdao.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.roomdao.R

@Composable
fun FavoriteIcon(tint: Color = MaterialTheme.colors.onPrimary) {
    Icon(
        imageVector = Icons.Filled.Favorite,
        contentDescription = stringResource(R.string.to_wish_list_button),
        tint = tint,
    )
}

@Composable
fun FavoriteOutlinedIcon() {
    Icon(
        imageVector = Icons.Outlined.FavoriteBorder,
        contentDescription = stringResource(R.string.to_wish_list_button),
        tint = MaterialTheme.colors.onPrimary,
    )
}

@Composable
fun SettingIcon() {
    Icon(
        Icons.Filled.Settings,
        contentDescription = stringResource(R.string.setting_icon),
        tint = MaterialTheme.colors.onPrimary
    )
}

@Composable
fun PlusIcon() {
    Icon(
        Icons.Filled.Add,
        contentDescription = stringResource(R.string.add_icon),
        tint = MaterialTheme.colors.onPrimary
    )
}
