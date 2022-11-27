package com.example.simbirsoft.presentation

import android.text.format.DateFormat
import java.util.*

class Utils {

    fun convertLongToTime(time: Long): String = String.format("%02d:%02d", time / 60, time % 60)

    fun convertDate(date: Date): String = DateFormat.format("MM:dd:yyyy", date.time).toString()

}