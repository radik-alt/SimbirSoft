package com.example.simbirsoft.domain.usecase

import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.data.repository.TaskRepository
import com.example.simbirsoft.data.room.DaoTask

class DeleteTaskUseCase(private val daoTask: DaoTask) {

    suspend fun deleteTask(task: Task){
        TaskRepository(daoTask).deleteTask(task)
    }

}