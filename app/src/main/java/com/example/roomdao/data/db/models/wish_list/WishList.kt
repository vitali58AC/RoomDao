package com.example.roomdao.data.db.models.wish_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//Получается этот класс не нужен?
@Entity(tableName = WishListContract.TABLE_NAME)
data class WishList(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = WishListContract.Columns.WISH_LIST_ID)
    val wishListId: Long,
    @ColumnInfo(name = WishListContract.Columns.USER_ID)
    val userId: Long,
    @ColumnInfo(name = WishListContract.Columns.PRODUCT_ID)
    val productId: Long
)
