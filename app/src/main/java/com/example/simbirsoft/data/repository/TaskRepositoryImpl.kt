package com.example.simbirsoft.data.repository

import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.data.room.DaoTask
import com.example.simbirsoft.data.room.DatabaseTask
import com.example.simbirsoft.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val daoTask: DaoTask): TaskRepository {

    override fun getListTask():Flow<List<Task>>{
        return daoTask.getAllTask()
    }

    override suspend fun addTask(task: Task){
        daoTask.addTask(task)
    }

    override suspend fun deleteTask(task: Task){
        daoTask.deleteTask(task)
    }

    override suspend fun updateTask(task: Task){
        daoTask.updateTask(task)
    }
}