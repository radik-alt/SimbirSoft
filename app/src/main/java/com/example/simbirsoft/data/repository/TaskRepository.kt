package com.example.simbirsoft.data.repository

import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.data.room.DaoTask
import com.example.simbirsoft.data.room.DatabaseTask
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val daoTask: DaoTask) {

    fun getListTask():Flow<List<Task>>{
        return daoTask.getAllTask()
    }

    suspend fun addTask(task: Task){
        daoTask.addTask(task)
    }

    suspend fun deleteTask(task: Task){
        daoTask.deleteTask(task)
    }

    suspend fun updateTask(task: Task){
        daoTask.updateTask(task)
    }
}