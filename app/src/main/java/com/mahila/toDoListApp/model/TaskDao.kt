package com.mahila.toDoListApp.model

import androidx.room.*
import com.mahila.toDoListApp.model.entity.Task


    @Dao
    interface TaskDao {
        @Query("SELECT * FROM task_table")
        suspend fun getAll(): List<Task>

        @Query("SELECT * FROM task_table WHERE taskId ==:tId")
        suspend fun loadById(tId: Int): Task
        @Query("SELECT * FROM task_table WHERE taskTitle ==:title")
        suspend fun loadByTitle(title: Int): Task

       /* @Query("SELECT * FROM task_table WHERE taskTitle LIKE :title AND ")
        fun findByTitle(title: String): Task*/
        @Update()
        suspend fun update(task: Task)
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert( tasks: Task)
        @Delete
        suspend fun delete(task: Task)
    }
