package com.example.roomdao.data.db.models.wish_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.roomdao.data.db.models.product.ProductContract
import com.example.roomdao.data.db.models.user.UserContract

@Entity(primaryKeys = [UserContract.Columns.PROFILE_ID, ProductContract.Columns.PRODUCT_ID])
data class UserProfileProductsCrossRef(
    @ColumnInfo(name = UserContract.Columns.PROFILE_ID)
    val userProfileId: Long,
    @ColumnInfo(name = ProductContract.Columns.PRODUCT_ID)
    val productId: Long
)
