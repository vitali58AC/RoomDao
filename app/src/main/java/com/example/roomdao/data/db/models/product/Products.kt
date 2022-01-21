package com.example.roomdao.data.db.models.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ProductContract.TABLE_NAME)
data class Products(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ProductContract.Columns.PRODUCT_ID)
    val productId: Long,
    @ColumnInfo(name = ProductContract.Columns.PRODUCT_NAME)
    val productName: String,
    @ColumnInfo(name = ProductContract.Columns.DESCRIPTION)
    val description: String,
    @ColumnInfo(name = ProductContract.Columns.IMAGE)
    val image: String?
)
