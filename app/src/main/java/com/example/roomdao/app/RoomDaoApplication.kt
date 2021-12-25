package com.example.roomdao.app

import android.app.Application
import com.example.roomdao.data.db.Database

class RoomDaoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        //Инициализировать при старте приложения
        Database.init(this)
    }
}