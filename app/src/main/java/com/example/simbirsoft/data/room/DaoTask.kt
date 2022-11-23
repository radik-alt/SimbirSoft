package com.example.simbirsoft.data.room

import androidx.room.*
import com.example.simbirsoft.data.Utils.ConvertDate
import com.example.simbirsoft.data.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
@TypeConverters(ConvertDate::class)
interface DaoTask {

    @Query("SELECT * FROM Task")
    fun getAllTask():Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}