package dev.kingbond.notify.ui.task.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
class TaskModel(
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "description")
    val desc:String,
    @ColumnInfo(name = "date")
    val date:String,
    @ColumnInfo(name = "time")
    val time:String,
    @ColumnInfo(name = "status")
    val status:Int,
    @ColumnInfo(name = "category")
    val category:String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int ?= null
}