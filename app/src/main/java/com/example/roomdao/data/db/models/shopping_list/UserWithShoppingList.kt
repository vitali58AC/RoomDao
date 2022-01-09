package com.example.roomdao.data.db.models.shopping_list

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomdao.data.db.models.user.User
import com.example.roomdao.data.db.models.user.UserContract

//Все списки покупок для пользователя
data class UserWithShoppingList(
    @Embedded val user: User,
    @Relation(
        parentColumn = UserContract.Columns.USER_ID,
        entityColumn = ShoppingListContract.Columns.SHOPPING_LIST_ID,
        associateBy = Junction(UserShoppingListCrossRef::class)
    )
    val shoppingLists: List<ShoppingList>
)

data class ShoppingListWithUser(
    @Embedded val shoppingList: ShoppingList,
    @Relation(
        parentColumn = ShoppingListContract.Columns.SHOPPING_LIST_ID,
        entityColumn = UserContract.Columns.USER_ID,
        associateBy = Junction(UserShoppingListCrossRef::class)
    )
    val users: List<User>
)
