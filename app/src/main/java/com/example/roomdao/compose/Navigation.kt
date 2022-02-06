package com.example.roomdao.compose

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.roomdao.compose.login.LoginScreen
import com.example.roomdao.compose.login.RegisterScreen
import com.example.roomdao.compose.shopping_lists.AddShoppingListScreen
import com.example.roomdao.compose.shopping_lists.DetailShoppingList
import com.example.roomdao.compose.shopping_lists.ShoppingListScreen
import com.example.roomdao.compose.user_profile.UserProfileScreen
import com.example.roomdao.compose.wish_list.WishListScreen
import com.example.roomdao.data.Screens
import com.example.roomdao.presentaion.ProductsViewModel
import com.example.roomdao.presentaion.ShoppingListViewModel
import com.example.roomdao.presentaion.UserViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    userViewModel: UserViewModel,
    shoppingListViewModel: ShoppingListViewModel,
    productsViewModel: ProductsViewModel,
    context: Context
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.name
    ) {
        composable(Screens.Login.name) {
            LoginScreen(
                onRegisterClick = { navController.navigate(Screens.Register.name) }
            ) { login, pass ->
                userViewModel.getUserByLoginAndPass(login, pass) { user ->
                    if (user != null) {
                        navigateToSingleAccount(navController, user.userId)
                    } else {
                        Toast.makeText(
                            context,
                            "User with mail $login is not registered",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        composable(Screens.Register.name) {
            RegisterScreen(
                onCancelClick = {
                    navController.navigate(Screens.Login.name) {
                        popUpTo(Screens.Login.name) { inclusive = true }
                    }
                },
                onRegisterClick = { args ->
                    if (args[4].isNotEmpty()) {

                        userViewModel.checkEmail(args[4]) { userExists ->
                            if (userExists) {
                                Toast.makeText(
                                    context,
                                    "User with email ${args[4]} is already exists",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                userViewModel.createUserAndProfile(args) { id ->
                                    navigateToSingleAccount(navController, id)
                                }
                            }
                        }
                    }
                },
                registerProgress =
                userViewModel.registerProgress.value
            )
        }
        composable(
            route = "${Screens.ShoppingList.name}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { entry ->
            val userId = entry.arguments?.getLong("id") ?: -1L
            SideEffect(shoppingListViewModel::getUserShopList, userId)
            ShoppingListScreen(
                shoppingList = shoppingListViewModel.shoppingLists,
                onShoppingListClick = { shoppingList ->
                    navController.navigate("${Screens.DetailShoppingList.name}/${shoppingList.id}/${userId}")
                },
                onAddShopListClick = {
                    navController.navigate("${Screens.AddShoppingList.name}/$userId")
                },
                onWishListClick = {
                    navController.navigate("${Screens.WishList.name}/$userId")
                }
            )
        }
        composable(Screens.Profile.name) {
            UserProfileScreen()
        }
        composable(
            route = "${Screens.AddShoppingList.name}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { entry ->
            val currentId = entry.arguments?.getLong("id") ?: -1L
            AddShoppingListScreen(
                createProgress = shoppingListViewModel.createProgress.value,
                onCancelClick = { navController.navigateUp() },
                onCreateClick = { shoppingList ->
                    shoppingListViewModel.insertShoppingList(currentId, shoppingList)
                    navController.navigateUp()
                }
            )
        }
        composable(
            route = "${Screens.DetailShoppingList.name}/{shoppingListId}/{userId}",
            arguments = listOf(
                navArgument("shoppingListId") { type = NavType.LongType },
                navArgument("userId") { type = NavType.LongType }
            )
        ) { entry ->
            val shoppingListId = entry.arguments?.getLong("shoppingListId") ?: -1L
            val userId = entry.arguments?.getLong("userId") ?: -1L
            Log.e("navigation", "shop id $shoppingListId userID $userId")
            SideEffect(func = productsViewModel::getProductList, currentId = shoppingListId)
            SideEffect(func = userViewModel::getWishListProducts, currentId = userId)
            DetailShoppingList(
                shoppingList = shoppingListViewModel.getCurrentShoppingList(shoppingListId),
                productsList = productsViewModel.productList,
                wishListProducts = userViewModel.productList,
                onSettingClick = { statusShoppingList ->
                    shoppingListViewModel.updateShoppingList(shoppingListId, statusShoppingList)
                },
                onAddProductClick = { products ->
                    productsViewModel.saveProduct(products, shoppingListId)
                },
                onAddToWishListClick = { productId ->
                    userViewModel.saveUserProducts(userId, productId)
                },
                onDeleteFromWishListClick = { productId ->
                    userViewModel.deleteUserProducts(userId, productId)
                }
            )
        }
        composable(
            route = "${Screens.WishList.name}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { entry ->
            val userId = entry.arguments?.getLong("id") ?: -1L
            SideEffect(func = userViewModel::getWishListProducts, currentId = userId)
            WishListScreen(
                productsList = userViewModel.productList,
                onDeleteFromWishListClick = { productId ->
                    userViewModel.deleteUserProducts(userId, productId)
                }
            )
        }
    }
}

private fun navigateToSingleAccount(
    navController: NavHostController,
    id: Long
) {
    navController.navigate("${Screens.ShoppingList.name}/$id") {
        popUpTo(Screens.Login.name) { inclusive = true }
    }
}


