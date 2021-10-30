package com.mahila.todolistapp.data.model
//-------------------------------*
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*
@Parcelize
@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int=0,//------------------**
    @ColumnInfo(name = "taskTitle")
    var taskTitle: String,
    @ColumnInfo(name = "taskDueDate")
    var taskDueDate: Date? = null, //or open time  ---------------------*
    //maybe without descr.
    @ColumnInfo(name = "taskDescription")
    var taskDescription: String? = null,
    var isCompleted:Boolean= false,
    val taskCreationDate: Date = Calendar.getInstance().time
) : Parcelable
//isCompleted