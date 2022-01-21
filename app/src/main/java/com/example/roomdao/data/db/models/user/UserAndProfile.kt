package com.example.roomdao.data.db.models.user

import androidx.room.Embedded
import androidx.room.Relation

//1-1 связь
data class UserAndProfile(
    @Embedded val user: User,
    @Relation(
        parentColumn = UserContract.Columns.USER_ID,
        entityColumn = UserContract.Columns.USER_OWNER_ID
    )
    val profile: UserProfile
)