package com.example.simbirsoft.presentation.adapter.Interface

import com.example.simbirsoft.data.entity.Task

interface OnClickTask {

    fun onClickTask(task: Task, delete:Boolean)

}