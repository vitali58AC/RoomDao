package com.example.roomdao.data.db.models

object UserContract {
    const val TABLE_NAME = "users"

    object Columns {
        const val ID = "id"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val EMAIL = "email"
        const val AVATAR = "avatar"
        const val PASSWORD_HASH = "password_hash"
    }
}