package com.mahila.toDoListApp.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mahila.toDoListApp.model.entity.Task
import com.mahila.toDoListApp.viewModel.TaskViewModel
import toDoListApp.R
import toDoListApp.databinding.FragmentInsertTaskInfoBinding
import java.util.*


class InsertTaskInfoFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModels()
    private var _binding: FragmentInsertTaskInfoBinding? = null
    private val binding get() = _binding!!
    private  var selectedDate: String="Open Time"
    private  var date: Date?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertTaskInfoBinding.inflate(layoutInflater, container, false)
        val calendar = Calendar.getInstance()

        binding.linearLayoutDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                binding.root.context,
                { view, year, monthOfYear, dayOfMonth ->
                   date= Date(year - 1900, monthOfYear+ 0, dayOfMonth+1)
                     selectedDate =
                         year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth
                    binding.tvTaskDueDate.setText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            //-----------------set to setMinDate
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis())
            datePickerDialog.show()


        }
        binding.btnOk.setOnClickListener {
            insertDataToDb(date)
        }
        binding.btnCan.setOnClickListener {
            findNavController().navigate(R.id.action_inaskInfoFragment_to_mainFragment)

        }
        return binding.root
    }


    private fun insertDataToDb(date: Date?) {


        if (binding.etTitle.text.toString().isNotEmpty()) {
            // Insert task to Database
            val task1 = Task(
                taskTitle = binding.etTitle.text.toString(),
                taskDueDate = date,
                taskDueDateAsString = selectedDate
                ,taskDescription = binding.etDesc.text.toString()
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

