package com.mahila.todolistapp

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mahila.todolistapp.data.model.Task

class TaskRecycleViewAdapter(private val taskList: List<Task>) :
    RecyclerView.Adapter<TaskAdapter>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent, false)
        return TaskAdapter(view)
    }

    override fun onBindViewHolder(holder: TaskAdapter, position: Int) {
        val task = taskList[position]
        holder.taskTitleTV.text = task.taskTitle

        holder.taskDueDateTV.text = "${android.text.format.DateFormat.format("EEEE", task.taskDueDate)}" +
                " \n${task.taskDueDate?.month.toString()}"
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}
//-------------inner*
class TaskAdapter(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val taskTitleTV: TextView = itemView.findViewById(R.id.tvTaskTitle)
    val taskDueDateTV: TextView = itemView.findViewById(R.id.tvTaskDueDate)

    /* init {
         //  itemView.setOnClickListener(this)
     }*/

    override fun onClick(v: View?) {
    }


}

