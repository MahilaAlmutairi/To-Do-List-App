package com.mahila.todolistapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mahila.todolistapp.data.Repo
import com.mahila.todolistapp.data.model.Task
import kotlinx.coroutines.launch

class MainViewModel(context: Application) : AndroidViewModel(context) {
    private val repo = Repo(context)


    fun getAll(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>> ()
        viewModelScope.launch {
            tasks.postValue(repo.getAll())
        }
        return tasks
    }


    fun fillDB(task:Task) = viewModelScope.launch {
        repo.fillDB(task)
    }

}