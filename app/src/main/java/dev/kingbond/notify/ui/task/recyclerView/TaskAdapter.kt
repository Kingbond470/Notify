package dev.kingbond.notify.ui.task.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ItemTaskLayoutBinding
import dev.kingbond.notify.databinding.TaskCompletedItemLayoutBinding
import dev.kingbond.notify.ui.task.model.TaskModel

class TaskAdapter(val list:ArrayList<TaskModel>, val taskClickListener: TaskClickListener):RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_task_layout,parent,false),
                taskClickListener
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = list[position]
        holder.setTask(task)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class TaskCompletedAdapter(val list:ArrayList<TaskModel>,val taskClickListener: TaskClickListener):RecyclerView.Adapter<TaskCompletedViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskCompletedViewHolder {

        return TaskCompletedViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.task_completed_item_layout,parent,false),taskClickListener)
    }

    override fun onBindViewHolder(holder: TaskCompletedViewHolder, position: Int) {

        val task = list[position]
        holder.setTask(task)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}



class TaskViewHolder(val itemTaskLayoutBinding: ItemTaskLayoutBinding,val taskClickListener: TaskClickListener):RecyclerView.ViewHolder(itemTaskLayoutBinding.root){
    fun setTask(taskModel: TaskModel){

        itemTaskLayoutBinding.task = taskModel

        itemTaskLayoutBinding.taskItemLayout.setOnClickListener {
            taskClickListener.taskItemClicked(taskModel)
        }

        itemTaskLayoutBinding.ivTaskCompleted.setOnClickListener {
            taskClickListener.taskCompletedClicked(taskModel)
            it.visibility = View.GONE
        }

        itemTaskLayoutBinding.ivTaskNotCompleted.setOnClickListener {
            taskClickListener.taskNotCompletedClicked(taskModel)
        }
    }
}

class TaskCompletedViewHolder(val itemTaskLayoutBinding: TaskCompletedItemLayoutBinding,val taskClickListener: TaskClickListener):RecyclerView.ViewHolder(itemTaskLayoutBinding.root){
    fun setTask(taskModel: TaskModel){

        itemTaskLayoutBinding.task = taskModel

        itemTaskLayoutBinding.taskItemLayout.setOnClickListener {
            taskClickListener.taskItemClicked(taskModel)
        }
    }
}