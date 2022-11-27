package com.example.simbirsoft.domain.usecase

import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.domain.repository.TaskRepository

class DeleteTaskUseCase(private val repository: TaskRepository) {

    suspend fun deleteTask(task: Task){
        repository.deleteTask(task)
    }

}