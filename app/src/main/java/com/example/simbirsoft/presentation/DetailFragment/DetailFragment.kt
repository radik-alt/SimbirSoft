package com.example.simbirsoft.presentation.DetailFragment

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.simbirsoft.R
import com.example.simbirsoft.databinding.FragmentDetailBinding
import com.example.simbirsoft.presentation.Utils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*


class DetailFragment : BottomSheetDialogFragment() {

    private val args : DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding?=null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailBinding == null")

    override fun onResume() {
        super.onResume()
        setData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setData(){
        binding.nameTask.setText(args.task.name)
        binding.descriptionTask.setText(args.task.name)
        binding.timeTask.text = "${Utils().convertLongToTime(args.task.timeStart)} : ${Utils().convertLongToTime(args.task.timeEnd)}"
        binding.dateTask.text = Utils().convertDate(args.task.date)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}