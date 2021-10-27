package com.mahila.todolistapp.data

import androidx.room.*
import com.mahila.todolistapp.data.model.Task


    @Dao
    interface TaskDao {
        @Query("SELECT * FROM task_table")
        suspend fun getAll(): List<Task>

        @Query("SELECT * FROM task_table WHERE taskId ==:tId")
        suspend fun loadById(tId: Int): Task

       /* @Query("SELECT * FROM task_table WHERE taskTitle LIKE :title AND ")
        fun findByTitle(title: String): Task*/
        @Update()
        suspend fun update(task: Task)
        @Insert
        suspend fun insert( tasks: Task)
        @Delete
        suspend fun delete(task: Task)
    }
