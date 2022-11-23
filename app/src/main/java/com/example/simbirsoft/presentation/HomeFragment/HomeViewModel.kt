package com.example.simbirsoft.presentation.HomeFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.data.repository.TaskRepository
import com.example.simbirsoft.data.room.DatabaseTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val listTask = MutableLiveData<List<Task>>()

    private val dao = DatabaseTask.getDatabaseNotes(application).myTaskDao()
    private val repositoryRoom = TaskRepository(dao)

    fun getListTaskRoom(){
        coroutine.launch {
            repositoryRoom.getListTask().collect{
                listTask.postValue(it)
            }
        }
    }

    fun deleteTask(task: Task){
        coroutine.launch {
            repositoryRoom.deleteTask(task)
        }
    }

    fun getListTask():LiveData<List<Task>> = listTask
}