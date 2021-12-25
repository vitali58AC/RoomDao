package com.example.roomdao.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdao.data.db.models.User

@Database(entities = [User::class], version = RoomDaoDatabase.DB_VERSION)
abstract class RoomDaoDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "room-dao-data"
    }
}