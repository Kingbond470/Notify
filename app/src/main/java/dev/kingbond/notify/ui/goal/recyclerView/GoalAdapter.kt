package dev.kingbond.notify.ui.goal.recyclerView

import android.view.LayoutInflater
import android.view.View
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
    private val goalClickListener: GoalClickListener,
    private val itemViewModelClass: ViewModelClass,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<GoalViewHolder>() {
internal class GoalAdapter(
    private var list: ArrayList<GoalModel>,
    private val goalClickListener: GoalClickListener,
    private val itemViewModelClass: ViewModelClass
) :
    RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    private var bool = BooleanArray(100)
    private val itemViewList = ArrayList<ItemGoalLayoutBinding>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_goal_layout,
                parent,
                false
            ), goalClickListener,
            itemViewModelClass,
            lifecycleOwner

        val view: ItemGoalLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_goal_layout,
            parent,
            false
        )
        itemViewList.add(view)

        return GoalViewHolder(
            view, goalClickListener, itemViewModelClass
        )
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val goal = list[position]
        holder.setGoalData(goal, bool, itemViewList, list)
    }

    override fun getItemCount(): Int {
        return list.size
    }

class GoalViewHolder(
    var itemGoalLayoutBinding: ItemGoalLayoutBinding,
    private val goalClickListener: GoalClickListener,
    private val itemViewModelClass: ViewModelClass,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(itemGoalLayoutBinding.root) {
    internal class GoalViewHolder(
        private var itemGoalLayoutBinding: ItemGoalLayoutBinding,
        private val goalClickListener: GoalClickListener,
        private val itemViewModelClass: ViewModelClass
    ) : RecyclerView.ViewHolder(itemGoalLayoutBinding.root) {

    fun setGoalData(goalModel: GoalModel) {
        itemGoalLayoutBinding.goal = goalModel
        itemGoalLayoutBinding.goalItem.setOnClickListener {
            goalClickListener.goalItemClicked(goalModel)
        internal fun setGoalData(goalModel: GoalModel, bool: BooleanArray, itemViewList: ArrayList<ItemGoalLayoutBinding>, list: ArrayList<GoalModel>) {
            itemGoalLayoutBinding.goal = goalModel

//            itemGoalLayoutBinding.goalItem.setOnClickListener {  }

            itemGoalLayoutBinding.goalItem.setOnClickListener {

//                Toast.makeText(itemGoalLayoutBinding.root.context, "Clicked -- ", Toast.LENGTH_SHORT).show()

//                if (bool[adapterPosition]) {
//                    itemGoalLayoutBinding.goalProgressBar.visibility = View.GONE
//                    bool[adapterPosition] = false
//                } else {
//                    check(bool, itemViewList, list)
//                    itemGoalLayoutBinding.goalProgressBar.visibility = View.VISIBLE
//                    bool[adapterPosition] = true
//                }
                goalClickListener.goalItemClicked(goalModel)
            }
        }

        var size = 0
        itemViewModelClass.getTasksOfGoal(goalModel.name).observe(lifecycleOwner, Observer {
            size = it.size
            itemGoalLayoutBinding.goalProgressBar.max = size
        })



        var completed = 0
        itemViewModelClass.getCompletedCountOfTask(goalModel.name).observe(lifecycleOwner, Observer {
            completed= it.size
            itemGoalLayoutBinding.goalProgressBar.progress = completed
        })



        private fun check(bool: BooleanArray, itemViewList: ArrayList<ItemGoalLayoutBinding>, list: ArrayList<GoalModel>) {
            for (i in bool.indices) {
                if (bool[i] && i!=adapterPosition) {
                    itemViewList[i].goalProgressBar.visibility = View.GONE
                }
            }
        }
    }
}
