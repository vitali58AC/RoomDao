package com.example.roomdao.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserContract.TABLE_NAME)
data class User(
    @PrimaryKey
    @ColumnInfo(name = UserContract.Columns.ID)
    val id: Int,
    @ColumnInfo(name = UserContract.Columns.FIRST_NAME)
    val firstName: String,
    @ColumnInfo(name = UserContract.Columns.LAST_NAME)
    val lastName: String,
    @ColumnInfo(name = UserContract.Columns.EMAIL)
    val email: String,
    @ColumnInfo(name = UserContract.Columns.AVATAR)
    val avatar: String?,
    @ColumnInfo(name = UserContract.Columns.PASSWORD_HASH)
    val passwordHash: String
)
