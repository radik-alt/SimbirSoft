package com.example.simbirsoft.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.simbirsoft.data.Utils.ConvertDate
import com.example.simbirsoft.data.entity.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(ConvertDate::class)
abstract class DatabaseTask: RoomDatabase() {

    abstract fun myTaskDao(): DaoTask

    companion object{
        @Volatile
        var INSTANCE: DatabaseTask?= null

        fun getDatabaseNotes(context: Context): DatabaseTask {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val roomDataBaseInstance = Room.databaseBuilder(
                    context,
                    DatabaseTask::class.java,
                    "TaskDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = roomDataBaseInstance
                return roomDataBaseInstance
            }
        }

    }

}