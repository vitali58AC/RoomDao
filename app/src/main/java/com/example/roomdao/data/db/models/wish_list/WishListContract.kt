package com.example.roomdao.data.db.models.wish_list

object WishListContract {
    const val TABLE_NAME = "wish_list"

    object Columns {
        const val WISH_LIST_ID = "wish_list_id"
        const val USER_ID = "user_id"
        const val PRODUCT_ID = "product_id"
    }
}