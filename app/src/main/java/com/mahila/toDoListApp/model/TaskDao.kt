package com.mahila.toDoListApp.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mahila.toDoListApp.model.entity.Task


    @Dao
    interface TaskDao {
        @Query("SELECT * FROM task_table")
        suspend fun getAll(): List<Task>
        @Query("SELECT * FROM task_table WHERE taskId ==:tId")
        suspend fun loadById(tId: Int): Task
        @Query("SELECT * FROM task_table WHERE taskTitle LIKE :searchQuery OR taskDescription LIKE :searchQuery")
        fun findByTitle(searchQuery: String): LiveData<List<Task>>
        @Update()
        suspend fun update(task: Task)
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert( tasks: Task)
        @Delete
        suspend fun delete(task: Task)
    }
