package dev.kingbond.notify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
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

    fun updateDataInTaskTable(taskModel: TaskModel){
        repo.updateToTask(taskModel)
    }

    fun getCompletedCountOfTask(goalName: String):LiveData<List<TaskModel>>{
        return repo.getCompletedTaskGoal(goalName)
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

    fun getOneGoal(goalName:String):LiveData<GoalModel>{
        return repo.getOneGoal(goalName)
    }

    fun getCompletedTask():LiveData<List<TaskModel>>{
        return repo.getCompletedTask()
    }

    fun getTasksByDate(date:String):LiveData<List<TaskModel>>{
        return repo.getTasksByDate(date)
    }
}