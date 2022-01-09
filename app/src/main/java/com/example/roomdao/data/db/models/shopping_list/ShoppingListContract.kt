package com.example.roomdao.data.db.models.shopping_list

object ShoppingListContract {
    const val TABLE_NAME = "shopping_list"

    object Columns {
        const val SHOPPING_LIST_ID = "shopping_List_Id"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val CREATE_AT = "create_at"
        const val BOUGHT_AT = "bought_at"
        const val PRICE = "price"
        const val STATUS = "status"
    }
}