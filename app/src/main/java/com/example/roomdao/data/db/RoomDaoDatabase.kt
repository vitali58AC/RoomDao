package com.example.roomdao.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdao.data.db.dao.ProductDao
import com.example.roomdao.data.db.dao.ShoppingListDao
import com.example.roomdao.data.db.dao.UserDao
import com.example.roomdao.data.db.models.product.Products
import com.example.roomdao.data.db.models.product.ShoppingListProductCrossRef
import com.example.roomdao.data.db.models.shopping_list.ShoppingList
import com.example.roomdao.data.db.models.shopping_list.UserShoppingListCrossRef
import com.example.roomdao.data.db.models.user.User
import com.example.roomdao.data.db.models.user.UserProfile
import com.example.roomdao.data.db.models.wish_list.UserProductsCrossRef

@Database(
    entities = [
        User::class,
        UserProfile::class,
        ShoppingList::class,
        UserShoppingListCrossRef::class,
        Products::class,
        ShoppingListProductCrossRef::class,
        UserProductsCrossRef::class
    ],
    version = RoomDaoDatabase.DB_VERSION
)
abstract class RoomDaoDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun productDao(): ProductDao

    companion object {
        const val DB_VERSION = 2
        const val DB_NAME = "room-dao-data"
    }
}


