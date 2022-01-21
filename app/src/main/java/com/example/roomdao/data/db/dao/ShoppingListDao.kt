package com.example.roomdao.data.db.dao

import androidx.room.*
import com.example.roomdao.data.db.models.shopping_list.*
import com.example.roomdao.data.db.models.user.User
import com.example.roomdao.data.db.models.user.UserContract

@Dao
interface ShoppingListDao {

    //Получение всех пользователей для списка покупок
    @Transaction
    @Query("SELECT * FROM ${ShoppingListContract.TABLE_NAME}")
    suspend fun getShoppingListWithUser(): List<ShoppingListWithUser>


    //Получение всех списков покупок для пользователя
    @Transaction
    @Query("SELECT * FROM ${UserContract.TABLE_NAME} WHERE ${UserContract.Columns.USER_ID} = :userId")
    suspend fun getUserWithShoppingList(userId: Long): UserWithShoppingList

    //Если нужно сразу получить сгенерированные id, то нужно указать возращаемый тип
    //Для списка это список idшников
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingLists(lists: List<ShoppingList>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserShoppingListCrossRef(relation: UserShoppingListCrossRef)

    @Update
    suspend fun updateShoppingList(shoppingList: ShoppingList): Int
}