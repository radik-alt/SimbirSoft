package com.example.simbirsoft.presentation.adapter

import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.simbirsoft.databinding.ItemTaskBinding

class TaskViewHolder(binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

    val nameTask = binding.nameTask
    val timeTask = binding.timeTask
    val checkTask = binding.checkTask

}