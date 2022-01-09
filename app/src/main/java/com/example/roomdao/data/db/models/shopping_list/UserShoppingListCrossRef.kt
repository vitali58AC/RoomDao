package com.example.roomdao.data.db.models.shopping_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.roomdao.data.db.models.user.UserContract

@Entity(primaryKeys = [ShoppingListContract.Columns.SHOPPING_LIST_ID, UserContract.Columns.USER_ID])
data class UserShoppingListCrossRef(
    @ColumnInfo(name = ShoppingListContract.Columns.SHOPPING_LIST_ID)
    val shoppingListId: Long,
    @ColumnInfo(name = UserContract.Columns.USER_ID)
    val userId: Long
)
