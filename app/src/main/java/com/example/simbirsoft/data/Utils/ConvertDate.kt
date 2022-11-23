package com.example.simbirsoft.data.Utils

import androidx.room.TypeConverter
import java.util.*

class ConvertDate {

    @TypeConverter
    fun dateToTimestamp(date: Date):Long {
        return date.time
    }

    @TypeConverter
    fun fromTimestamp(value: Long): Date{
        return Date(value)
    }
}