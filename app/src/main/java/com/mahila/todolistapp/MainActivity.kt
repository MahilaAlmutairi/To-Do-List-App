package com.mahila.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mahila.todolistapp.viewModel.TaskViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvRecycleView)
        val taskVm = ViewModelProvider(this).get(TaskViewModel::class.java)
           // val task = Task()
           // mainVm.fillDB(task)

           /* mainVm.getAll().observe(this, Observer {
                recyclerView.adapter = TaskRecycleViewAdapter(it)*/



        }
      //  recyclerView.layoutManager = LinearLayoutManager(this)

    }
