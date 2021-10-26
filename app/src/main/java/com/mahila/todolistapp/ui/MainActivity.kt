package com.mahila.todolistapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahila.todolistapp.R
import com.mahila.todolistapp.TaskRecycleViewAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvRecycleView)


        val mainVm = ViewModelProvider(this).get(MainViewModel::class.java)
           // val task = Task()
           // mainVm.fillDB(task)

           /* mainVm.getAll().observe(this, Observer {
                recyclerView.adapter = TaskRecycleViewAdapter(it)*/



        }
      //  recyclerView.layoutManager = LinearLayoutManager(this)

    }
}