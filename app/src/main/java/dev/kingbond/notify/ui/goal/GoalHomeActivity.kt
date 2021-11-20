package dev.kingbond.notify.ui.goal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kingbond.notify.databinding.ActivityGoalHomeBinding
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.ui.goal.model.GoalModel
import dev.kingbond.notify.ui.goal.recyclerView.GoalAdapter
import dev.kingbond.notify.ui.goal.recyclerView.GoalClickListener
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_goal_home.*
import java.io.Serializable

class GoalHomeActivity : AppCompatActivity(), GoalClickListener {

    private lateinit var binding : ActivityGoalHomeBinding

    private lateinit var adapter: GoalAdapter
    private lateinit var itemViewModel: ViewModelClass

    private var list = arrayListOf<GoalModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomDatabase = RoomDataBaseClass.getDataBaseObject(this)
        val dao = roomDatabase.getDao()
        val repo = RepositoryClass(dao)
        val viewModelFactory = ViewModelFactory(repo)
        itemViewModel = ViewModelProviders.of(this,viewModelFactory).get(ViewModelClass::class.java)


        itemViewModel.getDataFromGoal().observe(this, androidx.lifecycle.Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })

        setRecyclerView()
        binding.addGoalHome.setOnClickListener {
            val intent = Intent(this,GoalActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setRecyclerView() {
        adapter = GoalAdapter(list,this, itemViewModel)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.apply {
            goalRecyclerView.adapter = adapter
            goalRecyclerView.layoutManager = linearLayoutManager
        }
    }

    override fun goalItemClicked(goalModel: GoalModel) {
        val intent = Intent(this,GoalDetailsActivity::class.java)
        intent.putExtra("goalModel", goalModel as Serializable)
        startActivity(intent)
    }
}