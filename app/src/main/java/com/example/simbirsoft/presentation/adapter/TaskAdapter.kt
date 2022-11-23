package com.example.simbirsoft.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.databinding.ItemTaskBinding
import com.example.simbirsoft.presentation.adapter.Interface.OnClickTask
import java.util.*

class TaskAdapter(
    private var listTask : List<Task>,
    private val onClickTask: OnClickTask
) : RecyclerView.Adapter<TaskViewHolder>() {

    private var currentTime = Date().time

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        holder.nameTask.text = listTask[position].name
        holder.timeTask.text = "${convertLongToTime(listTask[position].timeStart)} : ${convertLongToTime(listTask[position].timeEnd)}"

        holder.checkTask.setOnClickListener {
            onClickTask.onClickTask(listTask[position], true)
        }

        holder.itemView.setOnClickListener {
            onClickTask.onClickTask(listTask[position], false)
        }
    }

    override fun getItemCount(): Int = listTask.size

    private fun convertLongToTime(time: Long): String = String.format("%02d:%02d", time / 60, time % 60)

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun updateList(_list:List<Task>){
        val diffUtil = DiffUtilsTask(listTask, _list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listTask = _list
        diffResult.dispatchUpdatesTo(this)
    }

    companion object{
        private const val AFTER = 2
        private const val NOW = 1
        private const val BEFORE = 0
    }

}