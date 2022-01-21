package com.example.roomdao.data.db.models.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = UserContract.TABLE_PROFILE,
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = [UserContract.Columns.USER_ID],
            childColumns = [UserContract.Columns.USER_OWNER_ID],
            onDelete = CASCADE
        )
    ]
)
data class UserProfile(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = UserContract.Columns.PROFILE_ID)
    val profileId: Long,
    @ColumnInfo(name = UserContract.Columns.FIRST_NAME)
    val firstName: String,
    @ColumnInfo(name = UserContract.Columns.LAST_NAME)
    val lastName: String,
    @ColumnInfo(name = UserContract.Columns.AVATAR)
    val avatar: String?,
    @ColumnInfo(name = UserContract.Columns.USER_OWNER_ID)
    val userOwnerId: Long
)