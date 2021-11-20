package dev.kingbond.notify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.ui.event.EventModel
import dev.kingbond.notify.ui.goal.model.GoalModel
import dev.kingbond.notify.ui.task.model.TaskModel

class ViewModelClass(val repo: RepositoryClass) : ViewModel() {


    fun insertDataIntoGoalTable(goalModel: GoalModel) {
        repo.addDataToGoal(goalModel)
    }

    fun getDataFromGoal(): LiveData<List<GoalModel>> {
        return repo.getAllGoals()
    }

    fun insertDataInTaskTable(taskModel: TaskModel) {
        repo.addDataToTask(taskModel)
    }

    fun getDataFromTask(): LiveData<List<TaskModel>> {
        return repo.getAllTasks()
    }

    fun getTasksOfGoal(goalName: String): LiveData<List<TaskModel>> {
        return repo.getGoalTask(goalName)
    }

    fun insertDataIntoEventTable(eventModel: EventModel) {
        repo.addDataToEvent(eventModel)
    }

    fun getDataFromEventTable(): LiveData<List<EventModel>> {
        return repo.getAllEvents()
    }
}