package com.mahila.toDoListApp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mahila.toDoListApp.model.entity.Task
import com.mahila.toDoListApp.model.repository.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(context: Application) : AndroidViewModel(context) {
    private val repo = Repo(context)

    fun getAll(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>>()
        viewModelScope.launch {
            tasks.postValue(repo.getAll())
        }
        return tasks
    }

    fun fillDB(task: Task) = viewModelScope.launch {
        repo.fillDB(task)
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTask(task)
        }
    }

    fun restoreDeleted(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.restoreDeleted(task)
        }
    }

    fun switchCompleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.switchCompleteTask(task)
        }
    }
    fun findByTitle(searchQuery: String): LiveData<List<Task>> {
        return repo.findByTitle(searchQuery)
    }
}

