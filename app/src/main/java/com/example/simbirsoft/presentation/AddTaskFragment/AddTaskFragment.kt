package com.example.simbirsoft.presentation.AddTaskFragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.databinding.FragmentAddTaskBinding
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
            TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                val tempStartTime = ((hour * 60) + minute).toLong()
                TimePickerDialog(it.context, TimePickerDialog.OnTimeSetListener { timePicker2, hour2, minute2 ->
                    val tempEndTime = ((hour2 * 60) + minute2).toLong()
                    if (addViewModel.validTime(tempStartTime, tempEndTime)){
                        addViewModel.timeStart = tempStartTime
                        addViewModel.timeEnd = tempEndTime
                        binding.timeTask.text = "${convertLongToTime(addViewModel.timeStart)} : ${convertLongToTime(addViewModel.timeEnd)}"
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

            DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                currentDate.set(year, monthOfYear, dayOfMonth)
                binding.dateTask.text = convertDate(currentDate.time)
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


            if (addViewModel.valid(task)){
                addViewModel.addTask(task)
                dismiss()
            }
        }

    }

    private fun convertLongToTime(time: Long): String = String.format("%02d:%02d", time / 60, time % 60)

    private fun convertDate(date: Date): String = DateFormat.format("MM:dd:yyyy", date.time).toString()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}