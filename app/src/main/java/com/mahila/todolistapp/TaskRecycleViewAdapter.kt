package com.mahila.todolistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mahila.todolistapp.data.model.Task

class TaskRecycleViewAdapter (private val taskList: List<Task>) : RecyclerView.Adapter<TaskAdapter>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent,false)
        return TaskAdapter(view)
    }
    override fun onBindViewHolder(holder: TaskAdapter, position: Int) {
        val task = taskList[position]
       //----------------
    }
    override fun getItemCount(): Int {
        return taskList.size
    }
}

class TaskAdapter(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    //-----------------
    init {
        itemView.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        //----------------------
    }
}

