package com.example.roomdao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.example.roomdao.compose.Navigation
import com.example.roomdao.presentaion.ShoppingListViewModel
import com.example.roomdao.presentaion.UserViewModel
import com.example.roomdao.ui.theme.RoomDaoTheme

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private val shoppingListViewModel: ShoppingListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            RoomDaoTheme {
                Surface(color = MaterialTheme.colors.background) {
                    window.statusBarColor = MaterialTheme.colors.onPrimary.toArgb()
                    Navigation(
                        navController = navController,
                        userViewModel = userViewModel,
                        shoppingListViewModel = shoppingListViewModel,
                        context = this
                    )
                }
            }
        }
    }
}