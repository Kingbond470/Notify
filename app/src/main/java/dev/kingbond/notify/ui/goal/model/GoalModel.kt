package dev.kingbond.notify.ui.goal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "goal_table")
data class GoalModel(
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "description")
    val desc:String,

    @ColumnInfo(name = "to_date")
    val to_date:String,

    @ColumnInfo(name = "from_date")
    val from_date:String,

    @ColumnInfo(name = "percent")
    val percent:Int
) :Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int ?= null

}