package dev.kingbond.notify.ui.goal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kingbond.notify.R
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.databinding.ActivityGoalDetailsBinding
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.ui.goal.model.GoalModel
import dev.kingbond.notify.ui.task.model.TaskModel
import dev.kingbond.notify.ui.task.recyclerView.TaskAdapter
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory

class GoalDetailsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityGoalDetailsBinding
    private lateinit var itemViewModel:ViewModelClass
    private lateinit var adapter:TaskAdapter
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
        itemViewModel = ViewModelProviders.of(this,viewModelFactory).get(ViewModelClass::class.java)

        itemViewModel.getTasksOfGoal(data.name).observe(this, Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })

        setRecyclerView()


    }

    private fun setRecyclerView() {

        adapter = TaskAdapter(list)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.apply {
            goalDetailsRecyclerView.adapter = adapter
            goalDetailsRecyclerView.layoutManager = linearLayoutManager
        }
    }
}