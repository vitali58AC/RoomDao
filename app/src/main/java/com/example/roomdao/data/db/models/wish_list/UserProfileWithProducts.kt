package com.example.roomdao.data.db.models.wish_list

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomdao.data.db.models.product.ProductContract
import com.example.roomdao.data.db.models.product.Products
import com.example.roomdao.data.db.models.user.UserContract
import com.example.roomdao.data.db.models.user.UserProfile

data class UserProfileWithProducts(
    @Embedded val userProfile: UserProfile,
    @Relation(
        parentColumn = UserContract.Columns.PROFILE_ID,
        entityColumn = ProductContract.Columns.PRODUCT_ID,
        associateBy = Junction(UserProfileProductsCrossRef::class)
    )
    val products: List<Products>
)
