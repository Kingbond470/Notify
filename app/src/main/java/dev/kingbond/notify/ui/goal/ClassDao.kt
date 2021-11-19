package dev.kingbond.notify.ui.goal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.kingbond.notify.ui.goal.model.GoalModel

@Dao
interface ClassDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataInGoal(goalModel: GoalModel)

    @Query("select * from goal_table")
    fun fetchDataFromGoal():LiveData<List<GoalModel>>
}