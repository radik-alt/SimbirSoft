package com.example.simbirsoft.domain.usecase

import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.data.repository.TaskRepository
import com.example.simbirsoft.data.room.DaoTask

class AddTaskUseCase(private val daoTask: DaoTask) {

    suspend fun addTask(task: Task){
        TaskRepository(daoTask).addTask(task)
    }

}