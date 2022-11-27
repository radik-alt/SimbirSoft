package com.example.simbirsoft.domain.usecase

import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.data.repository.TaskRepositoryImpl
import com.example.simbirsoft.data.room.DaoTask
import com.example.simbirsoft.domain.repository.TaskRepository

class AddTaskUseCase(private val repository: TaskRepository) {

    suspend fun addTask(task: Task){
        repository.addTask(task)
    }

}