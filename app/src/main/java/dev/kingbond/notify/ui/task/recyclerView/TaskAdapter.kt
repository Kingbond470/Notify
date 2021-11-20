package dev.kingbond.notify.ui.task.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ItemTaskLayoutBinding
import dev.kingbond.notify.databinding.TaskCompletedItemLayoutBinding
import dev.kingbond.notify.ui.task.model.TaskModel
import dev.kingbond.notify.viewmodel.ViewModelClass

class TaskAdapter(
    val list: ArrayList<TaskModel>,
    private val taskClickListener: TaskClickListener,
    private val itemViewModelClass: ViewModelClass,
    private val lifecycleOwner: LifecycleOwner,
) :
    RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_task_layout, parent, false
            ),
            taskClickListener,
            itemViewModelClass,
            lifecycleOwner
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

class TaskCompletedAdapter(
    val list: ArrayList<TaskModel>,
    private val taskClickListener: TaskClickListener,
    private val itemViewModelClass: ViewModelClass,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<TaskCompletedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskCompletedViewHolder {

        return TaskCompletedViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.task_completed_item_layout,
                parent,
                false
            ), taskClickListener,
            itemViewModelClass,
            lifecycleOwner
        )
    }

    override fun onBindViewHolder(holder: TaskCompletedViewHolder, position: Int) {

        val task = list[position]
        holder.setTask(task)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}


class TaskViewHolder(
    private val itemTaskLayoutBinding: ItemTaskLayoutBinding,
    private val taskClickListener: TaskClickListener,
    private val itemViewModelClass: ViewModelClass,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(itemTaskLayoutBinding.root) {
    fun setTask(taskModel: TaskModel) {

        itemTaskLayoutBinding.task = taskModel

        itemTaskLayoutBinding.taskItemLayout.setOnClickListener {
            taskClickListener.taskItemClicked(taskModel)
        }

        if(taskModel.status == 1) itemTaskLayoutBinding.ivTaskCompleted.visibility = View.GONE
        itemTaskLayoutBinding.ivTaskCompleted.setOnClickListener {
            taskClickListener.taskCompletedClicked(taskModel)
        }

        itemTaskLayoutBinding.ivTaskNotCompleted.setOnClickListener {
            taskClickListener.taskNotCompletedClicked(taskModel)
            itemTaskLayoutBinding.ivTaskCompleted.visibility = View.VISIBLE
        }
    }
}

class TaskCompletedViewHolder(
    private val itemTaskLayoutBinding: TaskCompletedItemLayoutBinding,
    private val taskClickListener: TaskClickListener,
    private val itemViewModelClass: ViewModelClass,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(itemTaskLayoutBinding.root) {
    fun setTask(taskModel: TaskModel) {

        itemTaskLayoutBinding.task = taskModel

        itemTaskLayoutBinding.taskItemLayout.setOnClickListener {
            taskClickListener.taskItemClicked(taskModel)
        }
    }
}