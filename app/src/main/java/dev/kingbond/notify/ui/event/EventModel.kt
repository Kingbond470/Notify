package dev.kingbond.notify.ui.event

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "event_table")
data class EventModel(
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "event_description")
    val event_description: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "time")
    val time: String,
/*
    @ColumnInfo(name = "start_point")
    val start_point: String,

    @ColumnInfo(name = "end_point")
    val end_point: String
    */
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

}