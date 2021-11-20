package dev.kingbond.notify.ui.goal.recyclerView

import dev.kingbond.notify.ui.goal.model.GoalModel

interface GoalClickListener {

    fun goalItemClicked(goalModel: GoalModel)
    fun goalItemAddClicked(goalModel: GoalModel)
}