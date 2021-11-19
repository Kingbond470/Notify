package dev.kingbond.notify.ui.goal.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ItemGoalLayoutBinding
import dev.kingbond.notify.ui.goal.model.GoalModel

class GoalAdapter(private val list:ArrayList<GoalModel>):RecyclerView.Adapter<GoalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_goal_layout,parent,false))
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val goal = list[position]
        holder.setGoalData(goal)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class GoalViewHolder(var itemGoalLayoutBinding: ItemGoalLayoutBinding):RecyclerView.ViewHolder(itemGoalLayoutBinding.root){

    fun setGoalData(goalModel: GoalModel){
        itemGoalLayoutBinding.goal = goalModel
    }
}