package com.mahila.todolistapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mahila.todolistapp.adapter.SwipeToDeleteOrToComplete
import com.mahila.todolistapp.adapter.TaskRecycleViewAdapter
import com.mahila.todolistapp.data.model.Task
import com.mahila.todolistapp.databinding.FragmentMainBinding
import com.mahila.todolistapp.viewModel.TaskViewModel

class MainFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter: TaskRecycleViewAdapter by lazy { TaskRecycleViewAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        // Setup RecyclerView
        setupRecyclerview()

        // Observe LiveData
        taskViewModel.getAll().observe(viewLifecycleOwner, { data ->
            adapter.setData(data)
            binding.rvRecycleView.scheduleLayoutAnimation()
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun swipeToDeleteOrTocomplete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDeleteOrToComplete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val swipedTask = adapter.tasksList[viewHolder.adapterPosition]
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        // Delete task
                        taskViewModel.deleteTask(swipedTask)
                        adapter.notifyItemRemoved(viewHolder.adapterPosition)
                        // Restore deleted task
                        restoreDeletedData(viewHolder.itemView, swipedTask)
                    }
                    ItemTouchHelper.RIGHT -> {
                        // Complete task
                        taskViewModel.switchCompleteTask(swipedTask)
                        adapter.notifyItemRemoved(viewHolder.adapterPosition)
                        // Undo Complete task
                        CompleteTaskOrUndo(viewHolder.itemView, swipedTask)
                    }
                }

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoreDeletedData(view: View, deletedTask: Task) {
        val snackBar = Snackbar.make(
            view, "Deleted '${deletedTask.taskTitle}'",
            Snackbar.LENGTH_LONG
        )
        snackBar.setAction("Undo") {
            taskViewModel.restoreDeleted(deletedTask)
        }
        snackBar.show()
    }

    private fun setupRecyclerview() {
        val recyclerView = binding.rvRecycleView
        recyclerView.adapter = adapter
        // Swipe to Delete or complete
        swipeToDeleteOrTocomplete(recyclerView)
    }

    private fun CompleteTaskOrUndo(view: View, completedTask: Task) {
        val msg=when(completedTask.isCompleted){
            "Completed"->"Uncompleted"
            "Uncompleted"->"Completed"
            else->""
        }
        val snackBar = Snackbar.make(
            view, "$msg '${completedTask.taskTitle}'",
            Snackbar.LENGTH_LONG
        )
        snackBar.setAction("Undo") {
            taskViewModel.switchCompleteTask(completedTask)
        }
        snackBar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}