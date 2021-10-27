package com.mahila.todolistapp.data.model
//-------------------------------*
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey
    val taskId: Int,
    val taskTitle: String,
    val taskDueDate: Date? = null, //or open time  ---------------------*
    //maybe without descr.
    val taskDescription: String?= null,
    val taskCreationDate: Date =Calendar.getInstance().time
)