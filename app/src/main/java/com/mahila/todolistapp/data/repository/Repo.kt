package com.mahila.todolistapp.data.repository

import android.content.Context
import com.mahila.todolistapp.data.database.AppDatabase
import com.mahila.todolistapp.data.model.Task
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
    suspend fun deleteTask(task: Task){
        appDB.taskDao.delete(task)
    }
    suspend fun restoreDeleted(task: Task){
        appDB.taskDao.insert(task)
    }
    suspend fun switchCompleteTask(task: Task){
        task.isCompleted=!(task.isCompleted)
        appDB.taskDao.update(task)
    }
}