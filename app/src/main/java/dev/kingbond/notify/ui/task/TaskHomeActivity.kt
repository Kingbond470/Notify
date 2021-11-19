package dev.kingbond.notify.ui.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kingbond.notify.R
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.databinding.ActivityTaskBinding
import dev.kingbond.notify.databinding.ActivityTaskHomeBinding
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.ui.task.model.TaskModel
import dev.kingbond.notify.ui.task.recyclerView.TaskAdapter
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory

class TaskHomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityTaskHomeBinding
    private lateinit var itemViewModel:ViewModelClass

    private lateinit var adapter:TaskAdapter

    private var list = arrayListOf<TaskModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomDatabase = RoomDataBaseClass.getDataBaseObject(this)
        val dao = roomDatabase.getDao()
        val repo = RepositoryClass(dao)
        val viewModelFactory = ViewModelFactory(repo)
        itemViewModel = ViewModelProviders.of(this,viewModelFactory).get(ViewModelClass::class.java)

        itemViewModel.getDataFromTask().observe(this, Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })

        setRecyclerView()
        binding.addTaskHome.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setRecyclerView() {
        adapter = TaskAdapter(list)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.apply {
            taskRecyclerView.adapter = adapter
            taskRecyclerView.layoutManager = linearLayoutManager
        }
    }
}