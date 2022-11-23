package com.example.simbirsoft.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "Task")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val date: Date,
    val timeStart: Long,
    val timeEnd: Long,
    val name: String,
    val description: String
):Parcelable
