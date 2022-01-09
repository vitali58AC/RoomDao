package com.example.roomdao.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdao.data.db.dao.ShoppingListDao
import com.example.roomdao.data.db.dao.UserDao
import com.example.roomdao.data.db.models.product.Products
import com.example.roomdao.data.db.models.product.ShoppingListProductCrossRef
import com.example.roomdao.data.db.models.shopping_list.ShoppingList
import com.example.roomdao.data.db.models.shopping_list.UserShoppingListCrossRef
import com.example.roomdao.data.db.models.user.User
import com.example.roomdao.data.db.models.user.UserProfile
import com.example.roomdao.data.db.models.wish_list.UserProfileProductsCrossRef

//Возможно в entities не хватает классов, или есть лишние
@Database(
    entities = [
        User::class,
        UserProfile::class,
        ShoppingList::class,
        UserShoppingListCrossRef::class,
        Products::class,
        ShoppingListProductCrossRef::class,
        UserProfileProductsCrossRef::class
    ],
    version = RoomDaoDatabase.DB_VERSION
)
abstract class RoomDaoDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun shoppingListDao(): ShoppingListDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "room-dao-data"
    }
}

//Так, я реализовал время и статус, далее добавить товары и пора реализовывать экраны и
// саму работу с табилцей
//И разобраться с FK!!
//Так, все таблицы добавлены, разбирайся с FK и делай экраны для списка покупок.


