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

    @ColumnInfo(name = "event_time")
    val event_time: String,

    @ColumnInfo(name = "transport_type")
    val transport_type: String,

    @ColumnInfo(name = "start_point")
    val start_point: String,

    @ColumnInfo(name = "start_point_latitude")
    val start_point_latitude: String,

    @ColumnInfo(name = "start_point_longitude")
    val start_point_longitude: String,

    @ColumnInfo(name = "end_point")
    val end_point: String,

    @ColumnInfo(name = "end_point_latitude")
    val end_point_latitude: String,

    @ColumnInfo(name = "end_point_longitude")
    val end_point_longitude: String,

    @ColumnInfo(name = "distance")
    val distance: String,

    @ColumnInfo(name = "alarm_time")
    val alarm_time: String,

    @ColumnInfo(name = "counter_time")
    val counter_time: String

) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

}