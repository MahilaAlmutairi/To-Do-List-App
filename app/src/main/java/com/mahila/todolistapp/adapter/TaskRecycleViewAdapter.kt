package com.mahila.todolistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mahila.todolistapp.data.model.Task
import com.mahila.todolistapp.databinding.RecycleViewItemBinding

class TaskRecycleViewAdapter() :
    RecyclerView.Adapter<TaskRecycleViewAdapter.TaskAdapter>() {
    var tasksList = emptyList<Task>()

    class TaskAdapter(private val binding: RecycleViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(task: Task) {
            binding.taskInfo = task
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TaskAdapter {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecycleViewItemBinding.inflate(layoutInflater, parent, false)
                return TaskAdapter(
                    binding
                )
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter {
        return TaskAdapter.from(
            parent
        )
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun onBindViewHolder(holder: TaskAdapter, position: Int) {
        val currentTask = tasksList[position]
        holder.bind(currentTask)
    }

    fun setData(tasks: List<Task>) {
        val toDoDiffUtil = TaskDiffUtil(tasksList, tasks)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.tasksList = tasks
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}


