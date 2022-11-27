package com.example.simbirsoft.presentation.AddTaskFragment

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.data.repository.TaskRepositoryImpl
import com.example.simbirsoft.data.room.DatabaseTask
import com.example.simbirsoft.domain.usecase.AddTaskUseCase
import com.example.simbirsoft.domain.usecase.GetListTaskUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class AddViewModel(application: Application):AndroidViewModel(application) {

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val dao = DatabaseTask.getDatabaseNotes(application).myTaskDao()
    private val repositoryRoom = TaskRepositoryImpl(dao)

    var timeStart:Long = 0L
    var timeEnd:Long = 0L
    var date: Date = Date()
    private var timeList = ArrayList<Task>()

    fun getAllWorkTime(){
        coroutine.launch{
            GetListTaskUseCase(repositoryRoom).getListTaskUseCase().collect{
                timeList.addAll(it)
            }
        }
    }

    fun addTask(task: Task){
        coroutine.launch {
            AddTaskUseCase(repositoryRoom).addTask(task)
        }
    }

    fun validErrorTask(task: Task):String?{
        return when{
            task.name.isEmpty() || task.name.trim().length<3 -> {
                errorName
            }
            validTimeOfSize(task.timeStart, task.timeEnd) ->{
                errorLengthTime
            }
            !validTime(task.timeStart, task.timeEnd) -> {
                errorExistTaskOfTime
            }
            else -> null
        }
    }

    private fun validTimeOfSize(selectStartTime:Long, selectEndTime:Long) = selectStartTime+ hour <= selectEndTime

    fun validTime(selectStartTime:Long, selectEndTime:Long):Boolean{
        var validTime = true

        val list = getFilterOfDateList()
        for (workTime in list){
            if (workTime.timeStart in selectStartTime..selectEndTime) {
                validTime = false
            }
            else if (workTime.timeEnd in selectStartTime..selectEndTime) {
                validTime = false
            }
        }

        return validTime
    }

    private fun getFilterOfDateList() = timeList.filter {
        it.date.date.compareTo(date.date) == 0
    }

    fun showToast(context: Context, error:String){
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    companion object{
        private const val hour = 60
        private const val errorName = "Неккоректный ввод названия"
        private const val errorLengthTime = "Минимальное время задачи 1 час"
        private const val errorExistTaskOfTime = "На это время уже есть задача! Можете удалить ее и назначить новую задачу!"
    }
}

