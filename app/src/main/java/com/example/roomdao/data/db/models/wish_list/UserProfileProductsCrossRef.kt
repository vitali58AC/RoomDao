package com.example.roomdao.data.db.models.wish_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.roomdao.data.db.models.product.ProductContract
import com.example.roomdao.data.db.models.user.UserContract

@Entity(primaryKeys = [UserContract.Columns.USER_ID, ProductContract.Columns.PRODUCT_ID])
data class UserProductsCrossRef(
    @ColumnInfo(name = UserContract.Columns.USER_ID)
    val userId: Long,
    @ColumnInfo(name = ProductContract.Columns.PRODUCT_ID)
    val productId: Long
)
