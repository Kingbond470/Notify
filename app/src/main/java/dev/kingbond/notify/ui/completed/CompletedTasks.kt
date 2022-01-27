package dev.kingbond.notify.ui.completed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kingbond.notify.R
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.databinding.FragmentCompletedTasksBinding
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.ui.task.model.TaskModel
import dev.kingbond.notify.ui.task.recyclerView.TaskClickListener
import dev.kingbond.notify.ui.task.recyclerView.TaskCompletedAdapter
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory


class CompletedTasks : Fragment(R.layout.fragment_completed_tasks),TaskClickListener {

    private lateinit var binding:FragmentCompletedTasksBinding
    private lateinit var adapter:TaskCompletedAdapter
    private var list = arrayListOf<TaskModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCompletedTasksBinding.bind(view)
        val roomDatabase = RoomDataBaseClass.getDataBaseObject(requireContext())
        val dao = roomDatabase.getDao()
        val repo = RepositoryClass(dao)

        val viewModelFactory = ViewModelFactory(repo)
        val itemViewModel = ViewModelProviders.of(this,viewModelFactory).get(ViewModelClass::class.java)

        itemViewModel.getCompletedTask().observe(viewLifecycleOwner, Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })
        adapter = TaskCompletedAdapter(list,this,itemViewModel,viewLifecycleOwner)
        binding.rcCompletedTaskRecyclerView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rcCompletedTaskRecyclerView.layoutManager = linearLayoutManager

    }

    override fun taskItemClicked(taskModel: TaskModel) {

    }

    override fun taskCompletedClicked(taskModel: TaskModel) {

    }

    override fun taskNotCompletedClicked(taskModel: TaskModel) {

    }

    override fun onTaskDeleteClicked(task: TaskModel) {

    }

}