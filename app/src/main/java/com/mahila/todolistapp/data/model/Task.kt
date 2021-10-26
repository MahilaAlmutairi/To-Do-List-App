package com.mahila.todolistapp.data.model
//-------------------------------*
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "task_table")
data class Task(


    @PrimaryKey
    val taskId1: Int,
    val taskTitle: String,
    val taskDueDate: Date?, //or open time  ---------------------*
    //taskCreationDate, I can getting it directly from the sys.  -------------
    val taskCreationDate: Date,
    val taskDescription: String
)