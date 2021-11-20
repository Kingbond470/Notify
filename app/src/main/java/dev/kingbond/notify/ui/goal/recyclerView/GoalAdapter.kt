package dev.kingbond.notify.ui.goal.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ItemGoalLayoutBinding
import dev.kingbond.notify.ui.goal.model.GoalModel
import dev.kingbond.notify.viewmodel.ViewModelClass

class GoalAdapter(
    private val list: ArrayList<GoalModel>,
    val goalClickListener: GoalClickListener,
    val itemviewModelClass: ViewModelClass,
    val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<GoalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_goal_layout,
                parent,
                false
            ), goalClickListener,
            itemviewModelClass,
            lifecycleOwner
        )
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val goal = list[position]
        holder.setGoalData(goal)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class GoalViewHolder(
    var itemGoalLayoutBinding: ItemGoalLayoutBinding,
    val goalClickListener: GoalClickListener,
    val itemviewModelClass: ViewModelClass,
    val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(itemGoalLayoutBinding.root) {

    fun setGoalData(goalModel: GoalModel) {


        itemGoalLayoutBinding.goal = goalModel
        itemGoalLayoutBinding.goalItem.setOnClickListener {
            goalClickListener.goalItemClicked(goalModel)
        }

        itemviewModelClass.getTasksOfGoal(goalModel.name).observe(lifecycleOwner, Observer {
            itemGoalLayoutBinding.goalProgressBar.max = it.size
        })

        itemviewModelClass.getCompletedCountOfTask(goalModel.name).observe(lifecycleOwner, Observer {
            itemGoalLayoutBinding.goalProgressBar.progress = it.size
        })
    }
}