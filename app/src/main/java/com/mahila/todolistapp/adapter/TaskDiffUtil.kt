package com.mahila.todolistapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mahila.todolistapp.data.model.Task

class TaskDiffUtil(
    private val oldList: List<Task>,
    private val newList: List<Task>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].taskId == newList[newItemPosition].taskId
                && oldList[oldItemPosition].taskTitle == newList[newItemPosition].taskTitle
                && oldList[oldItemPosition].taskDescription == newList[newItemPosition].taskDescription
          && oldList[oldItemPosition].isCompleted == newList[newItemPosition].isCompleted

    }
}