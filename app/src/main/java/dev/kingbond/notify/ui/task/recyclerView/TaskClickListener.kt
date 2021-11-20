package dev.kingbond.notify.ui.task.recyclerView

import dev.kingbond.notify.ui.task.model.TaskModel

interface TaskClickListener {

    fun taskItemClicked(taskModel: TaskModel)

    fun taskCompletedClicked(taskModel: TaskModel)

    fun taskNotCompletedClicked(taskModel: TaskModel)
}