package dev.kingbond.notify.ui.task.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ItemTaskLayoutBinding
import dev.kingbond.notify.ui.task.model.TaskModel

class TaskAdapter(val list:ArrayList<TaskModel>):RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_task_layout,parent,false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = list[position]
        holder.setTask(task)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class TaskViewHolder(val itemTaskLayoutBinding: ItemTaskLayoutBinding):RecyclerView.ViewHolder(itemTaskLayoutBinding.root){
    fun setTask(taskModel: TaskModel){
        itemTaskLayoutBinding.task = taskModel
    }
}