package com.example.roomdao.data.db.models.shopping_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.roomdao.data.db.models.user.User
import com.example.roomdao.data.db.models.user.UserContract

//Составной первичный ключ
@Entity(
    tableName = ShoppingListContract.Columns.SHOPPING_LIST_ID_CROSS_REF,
    primaryKeys = [ShoppingListContract.Columns.SHOPPING_LIST_ID, UserContract.Columns.USER_ID],
    foreignKeys = [
        ForeignKey(
            entity = ShoppingList::class,
            parentColumns = [ShoppingListContract.Columns.SHOPPING_LIST_ID],
            childColumns = [ShoppingListContract.Columns.SHOPPING_LIST_ID]
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = [UserContract.Columns.USER_ID],
            childColumns = [UserContract.Columns.USER_ID]
        )
    ]
)
data class UserShoppingListCrossRef(
    @ColumnInfo(name = ShoppingListContract.Columns.SHOPPING_LIST_ID)
    val shoppingListId: Long,
    @ColumnInfo(name = UserContract.Columns.USER_ID)
    val userId: Long
)