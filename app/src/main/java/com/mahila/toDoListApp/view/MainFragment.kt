package com.mahila.toDoListApp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mahila.toDoListApp.model.entity.Task
import com.mahila.toDoListApp.view.adapter.SwipeToDeleteOrToComplete
import com.mahila.toDoListApp.view.adapter.TaskRecycleViewAdapter
import com.mahila.toDoListApp.viewModel.TaskViewModel
import toDoListApp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TaskRecycleViewAdapter
    lateinit var taskList: MutableList<Task>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        setupRecyclerview()
        //search
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.search.clearFocus()
                if (query != null) {
                    searchThroughDatabase(query)
                }
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchThroughDatabase(newText)
                }
                return true
            }

        })
    }

    private fun swipeToDeleteOrTocomplete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDeleteOrToComplete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val swipedTask = adapter.tasksList[viewHolder.adapterPosition]
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        // Delete task
                        taskViewModel.deleteTask(swipedTask)
                        // adapter.notifyItemRemoved(viewHolder.adapterPosition)
                        taskList.removeAt(viewHolder.adapterPosition)
                        adapter = TaskRecycleViewAdapter(taskList)
                        binding.rvRecycleView.adapter = adapter

                        // Restore deleted task
                        restoreDeletedData(swipedTask)
                    }
                    ItemTouchHelper.RIGHT -> {
                        // Complete task
                        taskViewModel.switchCompleteTask(swipedTask)
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                        // Undo Complete task
                        completeTaskOrUndo(viewHolder.itemView, swipedTask)

                    }
                }

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoreDeletedData(deletedTask: Task) {
        val snackBar = Snackbar.make(
            binding.rvRecycleView, "Deleted '${deletedTask.taskTitle}'",
            Snackbar.LENGTH_LONG
        )
        snackBar.setAction("Undo") {
            taskViewModel.restoreDeleted(deletedTask)
            taskList.add(deletedTask)
            adapter = TaskRecycleViewAdapter(taskList)
            binding.rvRecycleView.adapter = adapter
        }
        snackBar.show()
    }

    private fun setupRecyclerview() {
        // Observe LiveData
        taskViewModel.getAll().observe(viewLifecycleOwner) { data ->
            taskList = data.toMutableList()
            adapter = TaskRecycleViewAdapter(data)
            binding.rvRecycleView.adapter = adapter
            binding.rvRecycleView.scheduleLayoutAnimation()
        }
        // Swipe to Delete or complete
        swipeToDeleteOrTocomplete(binding.rvRecycleView)
    }


    private fun completeTaskOrUndo(view: View, completedTask: Task) {
        val msg = when (completedTask.isCompleted) {
            true -> "Well Done!"
            false -> "Uncompleted"
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

    private fun searchThroughDatabase(query: String) {
        val searchQuery = "%$query%"
        taskViewModel.findByTitle(searchQuery).observe(viewLifecycleOwner) { list ->
            list?.let {
                adapter = TaskRecycleViewAdapter(list)
                binding.rvRecycleView.adapter = adapter
                binding.rvRecycleView.scheduleLayoutAnimation()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}