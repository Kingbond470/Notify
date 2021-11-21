package dev.kingbond.notify.ui.completed

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kingbond.notify.R
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.ui.task.model.TaskModel
import dev.kingbond.notify.ui.task.recyclerView.TaskClickListener
import dev.kingbond.notify.ui.task.recyclerView.TaskCompletedAdapter
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_completed.*
import org.eazegraph.lib.models.PieModel

class CompletedFragment : Fragment(R.layout.fragment_completed), TaskClickListener {

    private lateinit var itemViewModel: ViewModelClass
    private var tasksCompleted: Int = 0
    private var eventsCompleted: Int = 0
    private var goalsCompleted: Int = 0

    private lateinit var adapter: TaskCompletedAdapter
    private var list = arrayListOf<TaskModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val roomDatabase = RoomDataBaseClass.getDataBaseObject(requireContext())
        val dao = roomDatabase.getDao()
        val repo = RepositoryClass(dao)
        val viewModelFactory = ViewModelFactory(repo)
        itemViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ViewModelClass::class.java)

        itemViewModel.getDataFromGoal().observe(viewLifecycleOwner, Observer {
            goalsCompleted = it.size
            setData()
        })
        itemViewModel.getDataFromEventTable().observe(viewLifecycleOwner, Observer {
            eventsCompleted = it.size
            setData()
        })
        itemViewModel.getDataFromTask().observe(viewLifecycleOwner, Observer {
            tasksCompleted = it.size
            setData()
        })

        itemViewModel.getCompletedTask().observe(viewLifecycleOwner, Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })
        adapter = TaskCompletedAdapter(list,this,itemViewModel,viewLifecycleOwner)
        rcCompletedTaskRecyclerView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        rcCompletedTaskRecyclerView.layoutManager = linearLayoutManager

    }

    private fun setData() {

        pieChart.clearChart()

        // Set the percentage of language used
        tvTasks.text = tasksCompleted.toString()
        tvGoals.text = goalsCompleted.toString()
        tvEvents.text = eventsCompleted.toString()

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
            PieModel(
                "GoalsCompleted", tvTasks.text.toString().toInt().toFloat(),
                Color.parseColor("#66BB6A")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "GoalsInProgress", tvGoals.text.toString().toInt().toFloat(),
                Color.parseColor("#FFA726")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "GoalsNotStarted", tvEvents.text.toString().toInt().toFloat(),
                Color.parseColor("#EF5350")
            )
        )

        if(tasksCompleted == 0 && eventsCompleted == 0 && goalsCompleted == 0) {
            pieChart.addPieSlice(
                PieModel(
                    "Nothing", 0F,
                    Color.parseColor("#57D6C0")
                )
            )
        }

        // To animate the pie chart
        pieChart.startAnimation()
    }

    override fun taskItemClicked(taskModel: TaskModel) {

    }

    override fun taskCompletedClicked(taskModel: TaskModel) {

    }

    override fun taskNotCompletedClicked(taskModel: TaskModel) {
    }

}