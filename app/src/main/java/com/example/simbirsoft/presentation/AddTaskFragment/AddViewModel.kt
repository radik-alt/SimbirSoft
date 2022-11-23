package com.example.simbirsoft.presentation.AddTaskFragment

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.data.repository.TaskRepository
import com.example.simbirsoft.data.room.DatabaseTask
import com.example.simbirsoft.domain.entity.TimeUnvailbale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date

class AddViewModel(application: Application):AndroidViewModel(application) {

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val dao = DatabaseTask.getDatabaseNotes(application).myTaskDao()
    private val repositoryRoom = TaskRepository(dao)

    var timeStart:Long = 0L
    var timeEnd:Long = 0L
    var date: Date = Date()
    private var timeList = ArrayList<TimeUnvailbale>()

    fun getAllWorkTime(){
        coroutine.launch{
            repositoryRoom.getListTask().collect{
                for (i in it){
                    val timeUnvailbale = TimeUnvailbale(
                        startTime = i.timeStart,
                        endTime = i.timeEnd
                    )
                    timeList.add(timeUnvailbale)
                }
            }
        }
    }

    fun addTask(task: Task){
        coroutine.launch {
            repositoryRoom.addTask(task)
        }
    }

    fun valid(task: Task):Boolean{
        return if (task.name.isEmpty() ||
            task.name.trim().length<3){
            false
        } else validTime(task.timeStart, task.timeEnd)
    }

    fun validTime(startTime:Long, endTime:Long):Boolean{
        var correctTime = true
        for (time in timeList){
            if (startTime >= time.startTime && startTime <= time.endTime){
                correctTime = false
                break
            } else if (endTime <= time.endTime){
                correctTime = false
                break
            }
        }

        return if (startTime >= endTime+30){
            false
        } else true
    }



}