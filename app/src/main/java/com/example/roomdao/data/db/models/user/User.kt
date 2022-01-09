package com.example.roomdao.data.db.models.user

import androidx.room.*

@Entity(tableName = UserContract.TABLE_NAME)
data class User(
    //Если указать id = 0, то ключ сгенерируется сам
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = UserContract.Columns.USER_ID)
    val userId: Long,
    @ColumnInfo(name = UserContract.Columns.EMAIL)
    val email: String,
    @ColumnInfo(name = UserContract.Columns.PASSWORD_HASH)
    val passwordHash: String
)

@Entity(tableName = UserContract.TABLE_PROFILE)
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

//1-1 связь
data class UserAndProfile(
    @Embedded val user: User,
    @Relation(
        parentColumn = UserContract.Columns.USER_ID,
        entityColumn = UserContract.Columns.USER_OWNER_ID
    )
    val profile: UserProfile
)
