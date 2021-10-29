package com.mahila.todolistapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mahila.todolistapp.data.model.Task
import com.mahila.todolistapp.databinding.FragmentInsertTaskInfoBinding
import com.mahila.todolistapp.viewModel.TaskViewModel
import java.util.*


class InsertTaskInfoFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModels()
    private var _binding: FragmentInsertTaskInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertTaskInfoBinding.inflate(layoutInflater, container, false)
        val calendar = Calendar.getInstance()
        // add day of month
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        binding.tvTaskDueDate.setOnClickListener {
            DatePickerDialog(binding.root.context, { _, y, m, d ->

            }, year, month, day)
                .show()

        }
        binding.btnOk.setOnClickListener {
            insertDataToDb(Date(year, month, day))
        }
        binding.btnCan.setOnClickListener {
            findNavController().navigate(R.id.action_inaskInfoFragment_to_mainFragment)

        }
        return binding.root
    }


    private fun insertDataToDb(date:Date) {


        if (binding.etTitle.text.toString().isNotEmpty()) {
            // Insert task to Database
            val task1 = Task(
                taskTitle = binding.etTitle.text.toString(),
                taskDueDate = date,
                taskDescription = binding.etDesc.text.toString()
            )
            taskViewModel.fillDB(task1)

            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_inaskInfoFragment_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out the title field.", Toast.LENGTH_SHORT)
                .show()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

