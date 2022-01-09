package com.example.roomdao.data.db.models.product

object ProductContract {
    const val TABLE_NAME = "products"

    object Columns {
        const val PRODUCT_ID = "product_id"
        const val PRODUCT_NAME = "product_name"
        const val DESCRIPTION = "description"
        const val IMAGE = "image"
    }
}