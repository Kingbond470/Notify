package dev.kingbond.notify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.ui.goal.model.GoalModel

class ViewModelClass(val repo:RepositoryClass):ViewModel(){


    fun insertDataIntoGoalTable(goalModel: GoalModel){
        repo.addDataToGoal(goalModel)
    }
    fun getDataFromGoal():LiveData<List<GoalModel>>{
        return repo.getAllGoals()
    }
}