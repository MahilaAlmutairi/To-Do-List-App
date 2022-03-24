package com.mahila.toDoListApp.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.mahila.toDoListApp.model.database.AppDatabase
import com.mahila.toDoListApp.model.entity.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repo(context: Context) {
    private val appDB = AppDatabase.getAppDataBase(context)!!

    suspend fun getAll(): List<Task> = withContext(Dispatchers.IO) {
        appDB.taskDao.getAll()
    }

    suspend fun fillDB(task: Task) = withContext(Dispatchers.IO) {

        appDB.taskDao.insert(task)
    }

    suspend fun updateTask(task: Task) = withContext(Dispatchers.IO) {

        appDB.taskDao.update(task)
    }

    suspend fun deleteTask(task: Task)= withContext(Dispatchers.IO) {
        appDB.taskDao.delete(task)
    }

    suspend fun restoreDeleted(task: Task)= withContext(Dispatchers.IO) {
        appDB.taskDao.insert(task)
    }

    suspend fun switchCompleteTask(task: Task) = withContext(Dispatchers.IO){
        task.isCompleted = !(task.isCompleted)
        appDB.taskDao.update(task)
    }

    fun findByTitle(searchQuery: String): LiveData<List<Task>> {
        return appDB.taskDao.findByTitle(searchQuery)
    }
}