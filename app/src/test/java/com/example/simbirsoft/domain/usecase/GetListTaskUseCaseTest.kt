package com.example.simbirsoft.domain.usecase

import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.data.repository.TaskRepositoryImpl
import com.example.simbirsoft.data.room.DaoTask
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.util.*
import kotlin.collections.ArrayList

class GetListTaskUseCaseTest {


    @Test()
    fun testGetListData(){
        val taskRepository = mock<TaskRepositoryImpl>()
        val dao = mock<DaoTask>()

        val testTaskData = flow {
            val listTask = ArrayList<Task>()
            listTask.add(Task(null, Date(), 500, 620, "Сделать лабу по матеше", "подготовься к защите"))
            emit(listTask)
        }
        Mockito.`when`(taskRepository.getListTask()).thenReturn(testTaskData)

        val useCase = GetListTaskUseCase(taskRepository = taskRepository)
        val actual = useCase.getListTaskUseCase()

        Assert.assertEquals(testTaskData, actual)
    }

}