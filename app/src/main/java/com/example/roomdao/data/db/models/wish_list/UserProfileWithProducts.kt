package com.example.roomdao.data.db.models.wish_list

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomdao.data.db.models.product.ProductContract
import com.example.roomdao.data.db.models.product.Products
import com.example.roomdao.data.db.models.user.User
import com.example.roomdao.data.db.models.user.UserContract

data class UserWithProducts(
    @Embedded val user: User,
    @Relation(
        parentColumn = UserContract.Columns.USER_ID,
        entityColumn = ProductContract.Columns.PRODUCT_ID,
        associateBy = Junction(UserProductsCrossRef::class)
    )
    val products: List<Products>
)
