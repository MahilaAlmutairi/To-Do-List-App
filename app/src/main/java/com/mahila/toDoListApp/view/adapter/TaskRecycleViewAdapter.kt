package com.mahila.toDoListApp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahila.toDoListApp.model.entity.Task
import toDoListApp.databinding.RecycleViewItemBinding


class TaskRecycleViewAdapter(var tasksList: List<Task>) :
    RecyclerView.Adapter<TaskRecycleViewAdapter.TaskAdapter>() {

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



}


