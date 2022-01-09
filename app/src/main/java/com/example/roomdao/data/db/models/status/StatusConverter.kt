package com.example.roomdao.data.db.models.status

import androidx.room.TypeConverter

class StatusConverter {

    @TypeConverter
    fun convertStatusToString(status: StatusShoppingList): String = status.name

    @TypeConverter
    fun convertStringToConverter(statusString: String): StatusShoppingList =
        StatusShoppingList.valueOf(statusString)
}