package com.example.simbirsoft.domain.usecase

import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetListTaskUseCase (private val taskRepository: TaskRepository) {

    fun getListTaskUseCase(): Flow<List<Task>> {
        return taskRepository.getListTask()
    }

}