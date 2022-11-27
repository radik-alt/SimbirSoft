package com.example.simbirsoft.domain.repository

import com.example.simbirsoft.data.entity.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getListTask(): Flow<List<Task>>

    suspend fun addTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun updateTask(task: Task)
}