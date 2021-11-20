package dev.kingbond.notify.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.kingbond.notify.ui.goal.model.GoalModel
import dev.kingbond.notify.ui.task.model.TaskModel

@Dao
interface ClassDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataInGoal(goalModel: GoalModel)

    @Update
    fun updateDataInGoal(goalModel: GoalModel)

    @Query("select * from goal_table")
    fun fetchDataFromGoal():LiveData<List<GoalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataInTask(taskModel: TaskModel)

    @Update
    fun updateDataInTask(taskModel: TaskModel)

    @Query("select * from task_table")
    fun fetchDataFromTask():LiveData<List<TaskModel>>

    @Query("select * from task_table where category = :goalName")
    fun getTasksWithGoal(goalName: String): LiveData<List<TaskModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataInEvent(eventModel: EventModel)

    @Query("select * from event_table")
    fun fetchDataFromEvent(): LiveData<List<EventModel>>

}