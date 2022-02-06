package com.example.roomdao.data.db.models


import androidx.room.TypeConverter
import java.time.Instant

class TimeConverter {

    @TypeConverter
    fun instantToLong(instant: Instant) = instant.toEpochMilli()

    @TypeConverter
    fun timeLongToInstant(timeLong: Long): Instant = Instant.ofEpochMilli(timeLong)
}