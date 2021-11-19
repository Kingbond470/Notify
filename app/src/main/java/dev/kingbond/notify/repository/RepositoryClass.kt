package dev.kingbond.notify.repository

import androidx.lifecycle.LiveData
import dev.kingbond.notify.ui.goal.database.ClassDao
import dev.kingbond.notify.ui.goal.model.GoalModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryClass(val classDao: ClassDao) {

    fun addDataToGoal(goalModel: GoalModel){
        CoroutineScope(Dispatchers.IO).launch {
            classDao.insertDataInGoal(goalModel)
        }
    }

    fun getAllGoals():LiveData<List<GoalModel>>{
        return classDao.fetchDataFromGoal()
    }
}