package com.example.simbirsoft.presentation.AddTaskFragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.databinding.FragmentAddTaskBinding
import com.example.simbirsoft.presentation.Utils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*


class AddTaskFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddTaskBinding?= null
    private val binding:FragmentAddTaskBinding
        get() = _binding ?: throw RuntimeException("")

    private val addViewModel: AddViewModel by lazy {
        ViewModelProvider(this)[AddViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        addViewModel.getAllWorkTime()
        setData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.timeEventClick.setOnClickListener {
            TimePickerDialog(requireContext(), { timePickerStart, hourStart, minuteStart ->
                val tempStartTime = ((hourStart * 60) + minuteStart).toLong()

                TimePickerDialog(it.context, { timePickerEnd, hourEnd, minuteEnd ->
                    val tempEndTime = ((hourEnd * 60) + minuteEnd).toLong()
                    if (addViewModel.validTime(tempStartTime, tempEndTime)){
                        addViewModel.timeStart = tempStartTime
                        addViewModel.timeEnd = tempEndTime
                        binding.timeTask.text = "${Utils().convertLongToTime(addViewModel.timeStart)} : ${Utils().convertLongToTime(addViewModel.timeEnd)}"
                    } else {
                        addViewModel.showToast(requireContext(), errorExistTaskOfTime)
                    }
                }, 0, 0, true).show()
            }, 0, 0, true).show()
        }

        binding.dateEventClick.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val currentDate = Calendar.getInstance()

            DatePickerDialog(requireActivity(), { view, year, monthOfYear, dayOfMonth ->
                currentDate.set(year, monthOfYear, dayOfMonth)
                binding.dateTask.text = Utils().convertDate(currentDate.time)
            }, year, month, day).show()
        }

        binding.saveTask.setOnClickListener {

            val name = binding.nameTask.text.toString()
            val description = binding.descriptionTask.text.toString()

            val task = Task(
                id = null,
                date = addViewModel.date,
                timeStart = addViewModel.timeStart,
                timeEnd = addViewModel.timeEnd,
                name = name,
                description = description
            )

            val error = addViewModel.validErrorTask(task)
            if (error == null){
                addViewModel.addTask(task)
                dismiss()
            } else {
                addViewModel.showToast(requireContext(), error)
            }
        }

    }

    private fun setData() {
        binding.dateTask.text = Utils().convertDate(Date())
        binding.timeTask.text = Utils().convertLongToTime(Date().time)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        private const val errorExistTaskOfTime = "На это время уже есть задача! Можете удалить ее и назначить новую задачу!"
    }

}