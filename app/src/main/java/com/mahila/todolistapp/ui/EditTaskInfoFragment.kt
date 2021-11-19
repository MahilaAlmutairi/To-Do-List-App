package com.mahila.todolistapp.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mahila.todolistapp.R
import com.mahila.todolistapp.data.model.Task
import com.mahila.todolistapp.databinding.FragmentEditTaskInfoBinding
import com.mahila.todolistapp.viewModel.TaskViewModel
import java.util.*

class EditTaskInfoFragment : Fragment() {
    private val args by navArgs<EditTaskInfoFragmentArgs>()

    private val taskViewModel: TaskViewModel by viewModels()

    private var _binding: FragmentEditTaskInfoBinding? = null
    private val binding get() = _binding!!
    private var selectedDate1: String = "Open Time"
    private var date1: Date? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        _binding = FragmentEditTaskInfoBinding.inflate(inflater, container, false)
        binding.args1 = args

        if ((args.currentTask.taskDueDate)?.compareTo(Calendar.getInstance().time) == -1) {
            val updatedTask = Task(
                args.currentTask.taskId,
                args.currentTask.taskTitle,
                args.currentTask.taskDueDate,
                "Past Due",
                args.currentTask.taskDescription,
                args.currentTask.isCompleted,
                true //del
            )
            taskViewModel.updateTask(updatedTask)
             binding.currentDueDateEt.visibility=View.GONE
             binding.currentPastDue.visibility=View.VISIBLE
        }
        setHasOptionsMenu(true)
        val calendar = Calendar.getInstance()

        binding.currentDueDateEt.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                binding.root.context,
                { view, year, monthOfYear, dayOfMonth ->
                    date1 = Date(year - 1900, monthOfYear, dayOfMonth+1)

                    selectedDate1 =
                        year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth
                    binding.currentDueDateEt.setText(selectedDate1)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
          //  datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis())
            datePickerDialog.show()


        }
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_and_delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateTask()
            R.id.menu_delete -> confirmDelete()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateTask() {

        val title = binding.currentTitleEt.text.toString()
        val description = binding.currentDescriptionEt.text.toString()

        if (!(title.isEmpty())) {
            // Update Current Task
            val updatedTask = Task(
                args.currentTask.taskId,
                title,
                date1,
                selectedDate1,
                description,
                args.currentTask.isCompleted,
            )
            taskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
            // Navigate back
            findNavController().navigate(R.id.action_editTaskInfoFragment_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out the title field.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    // Show AlertDialog for Confirming delete the task
    private fun confirmDelete() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            taskViewModel.deleteTask(args.currentTask)
            Toast.makeText(
                requireContext(),
                "Successfully Deleted: ${args.currentTask.taskTitle}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_editTaskInfoFragment_to_mainFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete '${args.currentTask.taskTitle}'?")
        builder.setMessage("Are you sure you want to delete '${args.currentTask.taskTitle}'?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
