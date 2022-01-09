package com.example.roomdao.data.db.models

import android.os.Build
import androidx.room.TypeConverter
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class TimeConverter {

    @TypeConverter
    fun instantToLong(instant: Instant) = instant.toEpochMilli()

    @TypeConverter
    fun timeLongToInstant(timeLong: Long): Instant = Instant.ofEpochMilli(timeLong)
}