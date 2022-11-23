package com.example.simbirsoft.domain.usecase

import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.data.repository.TaskRepository
import com.example.simbirsoft.data.room.DaoTask
import kotlinx.coroutines.flow.Flow

class GetListTaskUseCase (private val daoTask: DaoTask) {

    fun getListTaskUseCase(): Flow<List<Task>> {
        return TaskRepository(daoTask).getListTask()
    }

}