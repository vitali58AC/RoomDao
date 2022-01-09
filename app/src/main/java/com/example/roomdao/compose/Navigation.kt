package com.example.roomdao.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.roomdao.compose.login.LoginScreen
import com.example.roomdao.compose.shopping_lists.ShoppingListScreen
import com.example.roomdao.compose.user_profile.UserProfileScreen
import com.example.roomdao.data.Screens
import com.example.roomdao.presentaion.UserViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.name
    ) {
        composable(Screens.Login.name) {
            LoginScreen()
        }
        composable(Screens.ShoppingList.name) {
            ShoppingListScreen()
        }
        composable(Screens.Profile.name) {
            UserProfileScreen()
        }
    }

}