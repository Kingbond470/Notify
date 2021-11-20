package dev.kingbond.notify.ui.goal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import dev.kingbond.notify.R
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.databinding.ActivityGoalDetailsBinding
import dev.kingbond.notify.databinding.TaskDialogueLayoutBinding
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.ui.goal.model.GoalModel
import dev.kingbond.notify.ui.task.TaskGoalActivity
import dev.kingbond.notify.ui.task.model.TaskModel
import dev.kingbond.notify.ui.task.recyclerView.TaskAdapter
import dev.kingbond.notify.ui.task.recyclerView.TaskClickListener
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory

class GoalDetailsActivity : AppCompatActivity(), TaskClickListener {

    private lateinit var binding: ActivityGoalDetailsBinding
    private lateinit var itemViewModel: ViewModelClass
    private lateinit var adapter: TaskAdapter
    private var list = arrayListOf<TaskModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomDatabase = RoomDataBaseClass.getDataBaseObject(this)
        val dao = roomDatabase.getDao()
        val repo = RepositoryClass(dao)
        val viewModelFactory = ViewModelFactory(repo)
        val data = intent.getSerializableExtra("goalModel") as GoalModel
        binding.apply {
            goalDetailsTitle.text = data.name
            goalDetailsDesc.text = data.desc
            toGoalDetails.text = data.to_date
            fromGoalDetails.text = data.from_date
        }
        itemViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ViewModelClass::class.java)

        itemViewModel.getTasksOfGoal(data.name).observe(this, Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })

        setRecyclerView()
        addTaskToGoal()
    }

    private fun addTaskToGoal() {
        binding.addTaskToGoal.setOnClickListener {
            val intent = Intent(this, TaskGoalActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setRecyclerView() {

        adapter = TaskAdapter(list, this)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.apply {
            goalDetailsRecyclerView.adapter = adapter
            goalDetailsRecyclerView.layoutManager = linearLayoutManager
        }
    }

    override fun taskItemClicked(taskModel: TaskModel) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.task_dialogue_layout, null)
        val taskDialogLayoutBinding = TaskDialogueLayoutBinding.bind(view)
        bottomSheetDialog.setContentView(taskDialogLayoutBinding.root)
        bottomSheetDialog.setCancelable(false)

        taskDialogLayoutBinding.apply {
            dialogTaskName.text = taskModel.name
            dialogTaskDesc.text = taskModel.desc
            dialogTaskDate.text = taskModel.date
            dialogTaskTime.text = taskModel.time
        }
        bottomSheetDialog.show()
        taskDialogLayoutBinding.okDialogTask.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
    }
}