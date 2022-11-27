package com.example.simbirsoft.domain.usecase

import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.data.repository.TaskRepositoryImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.util.Date

class AddTaskUseCaseTest {

    private val taskRepository = mock<TaskRepositoryImpl>()

    @Test
    suspend fun addTask(){

        val testAddTask = Task(null, Date(), 600, 720, "Top task", "no description")
        Mockito.`when`(taskRepository.addTask(testAddTask)).thenReturn(Unit)

        val task = Task(null, Date(), 600, 720, "Top task", "no description")

        val useCase = GetListTaskUseCase(taskRepository).getListTaskUseCase().toList()

        Assert.assertEquals(task , useCase)
    }

}