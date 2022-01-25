package com.example.roomdao.data.db.models.user

import androidx.room.*

@Entity(
    tableName = UserContract.TABLE_NAME,
    //Для того, чтобы сделать поле уникальным, нужно добавить на него индекс
    indices = [Index(UserContract.Columns.EMAIL, unique = true)]
)
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
