package com.example.simbirsoft.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.simbirsoft.data.entity.Task

class DiffUtilsTask(
    private val oldListTask: List<Task>,
    private val newListTask: List<Task>,
    ): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldListTask.size

    override fun getNewListSize(): Int = newListTask.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListTask[oldItemPosition].id == newListTask[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListTask[oldItemPosition] == newListTask[newItemPosition]
    }
}