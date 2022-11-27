package com.example.simbirsoft.presentation.HomeFragment

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.data.repository.TaskRepositoryImpl
import com.example.simbirsoft.data.room.DatabaseTask
import com.example.simbirsoft.domain.usecase.DeleteTaskUseCase
import com.example.simbirsoft.domain.usecase.GetListTaskUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val listTaskData = MutableLiveData<List<Task>>()

    private val dao = DatabaseTask.getDatabaseNotes(application).myTaskDao()
    private val repositoryRoom = TaskRepositoryImpl(dao)

    private var selectDate: Date = Date()
    private var listTaskSort: ArrayList<Task> = ArrayList()

    fun setSelectDate(_selectDate:Date){
        selectDate = _selectDate
        getListTaskRoom()
    }

    fun getSelectDate(): Date{
        return selectDate
    }

    fun getListTaskRoom(){
        coroutine.launch {
            GetListTaskUseCase(repositoryRoom).getListTaskUseCase().collect{
                listTaskSort.clear()
                listTaskSort.addAll(it)
                getListTaskSortOfDate()
            }
        }
    }

    private fun getListTaskSortOfDate(){
        val resultSort = listTaskSort.filter {
            it.date.date.compareTo(selectDate.date)== 0
        }
        listTaskData.postValue(resultSort)
    }

    fun deleteTask(task: Task){
        coroutine.launch {
            DeleteTaskUseCase(repositoryRoom).deleteTask(task)
        }
    }

    fun showToast(context: Context, error:String){
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    fun getListTask():LiveData<List<Task>> = listTaskData
}