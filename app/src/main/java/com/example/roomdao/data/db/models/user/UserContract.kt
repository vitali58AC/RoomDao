package com.example.roomdao.data.db.models.user

object UserContract {
    const val TABLE_NAME = "users"
    const val TABLE_PROFILE = "user_profiles"

    object Columns {
        const val USER_ID = "user_id"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val EMAIL = "email"
        const val AVATAR = "avatar"
        const val PASSWORD_HASH = "password_hash"
        const val PROFILE_ID = "profile_id"
        const val USER_OWNER_ID = "USER_OWNER_ID"
    }
}