package com.mahila.todolistapp

import android.content.ContentValues.TAG
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mahila.todolistapp.data.model.Task
import com.mahila.todolistapp.viewModel.TaskViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addsBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvRecycleView)
        addsBtn = findViewById(R.id.addingBtn)

        val taskVm = ViewModelProvider(this).get(TaskViewModel::class.java)
       /* val task1 = Task(
            taskTitle = "taskTitle",
            taskDueDate = Date(2023, 5, 5),
            taskDescription = "dddd"
        )*/
        addsBtn.setOnClickListener {

            GettingTaskInfoFragment().show(supportFragmentManager, GettingTaskInfoFragment.TAG)
           // taskVm.fillDB(task1)
            taskVm.getAll().observe(this, Observer {
                recyclerView.adapter = TaskRecycleViewAdapter(it)

            })

        }


        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}
