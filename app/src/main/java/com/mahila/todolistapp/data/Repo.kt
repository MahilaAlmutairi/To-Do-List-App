package com.mahila.todolistapp.data

import android.content.Context
import com.mahila.todolistapp.data.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repo(context: Context) {
    private val appDB = AppDatabase.getAppDataBase(context)!!

    suspend fun getAll(): List<Task> = withContext(Dispatchers.IO) {
        appDB.taskDao.getAll()
    }

    suspend fun fillDB(task:Task) = withContext(Dispatchers.IO) {

        appDB.taskDao.insert(task)}
}