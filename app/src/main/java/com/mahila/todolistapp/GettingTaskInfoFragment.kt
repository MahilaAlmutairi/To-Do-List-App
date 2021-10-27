package com.mahila.todolistapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.mahila.todolistapp.data.model.Task
import com.mahila.todolistapp.viewModel.TaskViewModel
import java.time.LocalDate
import java.util.*


class GettingTaskInfoFragment : DialogFragment() {
    private lateinit var Title: EditText
    private lateinit var TaskDueDate: TextView
    private lateinit var Desc: EditText

    private lateinit var okBtn: Button
    private lateinit var cancelBtn: Button
    companion object {

        const val TAG = "GettingTaskInfoFragment"

    }

    //  private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_getting_task_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // viewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        val taskVm = ViewModelProvider(this).get(TaskViewModel::class.java)
        okBtn = view.findViewById(R.id.btnOk)
        cancelBtn = view.findViewById(R.id.btnCan)
        Title = view.findViewById(R.id.etTitle)
        TaskDueDate = view.findViewById(R.id.tvTaskDueDate)
        Desc = view.findViewById(R.id.etDesc)
        val calendar = Calendar.getInstance()
        // add day of month
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        TaskDueDate.setOnClickListener {
            DatePickerDialog(view.context, { _, y, m, d ->

            }, year, month, day)
                .show()

        }

        okBtn.setOnClickListener {
            val task1 = Task(
                taskTitle = Title.text.toString(),
                taskDueDate = Date(year, month, day),
                taskDescription = Desc.text.toString()
            )
            taskVm.fillDB(task1)
            dismiss()           /* taskVm.getAll().observe(this, Observer {
                recyclerView.adapter = TaskRecycleViewAdapter(it)

            })*/

        }

        cancelBtn.setOnClickListener {
            dismiss()

        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    /*  private fun setupClickListeners(view: View) {

      }*/

}