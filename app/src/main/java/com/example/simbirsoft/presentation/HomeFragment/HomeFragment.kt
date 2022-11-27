package com.example.simbirsoft.presentation.HomeFragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.simbirsoft.R
import com.example.simbirsoft.data.entity.Task
import com.example.simbirsoft.databinding.FragmentHomeBinding
import com.example.simbirsoft.presentation.AddTaskFragment.AddTaskFragment
import com.example.simbirsoft.presentation.Utils
import com.example.simbirsoft.presentation.adapter.Interface.OnClickTask
import com.example.simbirsoft.presentation.adapter.TaskAdapter
import java.util.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?=null
    private val binding:FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding == null")

    private val homeViewModel : HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getListTaskRoom()
        setAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setAdapter(){
        homeViewModel.getListTask().observe(viewLifecycleOwner){
            val taskAdapter = TaskAdapter(it, object : OnClickTask{
                override fun onClickTask(task: Task, delete: Boolean) {
                    if (delete){
                        homeViewModel.deleteTask(task)
                    } else {
                        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(task)
                        findNavController().navigate(action)
                    }
                }
            })
            taskAdapter.updateList(it)
            binding.recyclerTask.adapter = taskAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.customToolbar.addTask.setOnClickListener {
            showBottomFragment()
        }

        binding.selectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)


            DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val currentDate = Calendar.getInstance()
                currentDate.set(year, monthOfYear, dayOfMonth)
                homeViewModel.setSelectDate(currentDate.time)

                val selectDate = homeViewModel.getSelectDate()
                binding.selectDate.text = Utils().convertDate(selectDate)
            }, year, month, day).show()
        }
    }


    private fun showBottomFragment(){
        val bottomFragment = AddTaskFragment()
        bottomFragment.show(parentFragmentManager, bottomFragment.tag)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }
}