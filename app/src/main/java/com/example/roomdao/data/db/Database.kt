package com.example.roomdao.data.db

import android.content.Context
import androidx.room.Room

//Объект базы данных должен быть в единственном экземпляре
object Database {

    lateinit var instance: RoomDaoDatabase

    fun init(context: Context) {
        //Связать instance с файломи базы данных
        instance = Room.databaseBuilder(
            //Создаёт базу данных в оперативной памяти, при перезапуске очищается(для тестирования)
            //instance = Room.inMemoryDatabaseBuilder(
            context,
            RoomDaoDatabase::class.java,
            RoomDaoDatabase.DB_NAME
        )
            .addMigrations(MIGRATION_1_2)
            //Полностью пересоздаёт DB, только для разработки! Нельзя оставлять для проекта
            //.fallbackToDestructiveMigration()
            .build()
    }
}