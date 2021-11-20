package dev.kingbond.notify.ui.calendar

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.notify.R
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.databinding.ActivityGoalDetailsBinding
import dev.kingbond.notify.databinding.FragmentCalendarBinding
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.ui.goal.model.GoalModel
import dev.kingbond.notify.ui.goal.recyclerView.GoalAdapter
import dev.kingbond.notify.ui.goal.recyclerView.GoalClickListener
import dev.kingbond.notify.ui.task.model.TaskModel
import dev.kingbond.notify.ui.task.recyclerView.TaskAdapter
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class CalendarFragment : Fragment(R.layout.fragment_calendar), DateClickListener, GoalClickListener {

    private var selectedDate: LocalDate? = null
    private lateinit var calendarAdapter: CalendarAdapter

    private lateinit var binding: FragmentCalendarBinding
    private lateinit var itemViewModel: ViewModelClass
    private lateinit var goalAdapter: GoalAdapter
    private var listGoal = arrayListOf<GoalModel>()
    private lateinit var daysInMonth: ArrayList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCalendarBinding.bind(view)

        val roomDatabase = RoomDataBaseClass.getDataBaseObject(requireContext())
        val dao = roomDatabase.getDao()
        val repo = RepositoryClass(dao)
        val viewModelFactory = ViewModelFactory(repo)
        itemViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ViewModelClass::class.java)

        rcvGoals()

        selectedDate = LocalDate.now()
        setMonthView()
        jumpToDate()
    }

    private fun rcvGoals() {
        itemViewModel.getDataFromGoal().observe(viewLifecycleOwner, Observer {
//            listGoal.clear()
            listGoal.addAll(it)
            listGoal.addAll(it)
            listGoal.addAll(it)
            listGoal.addAll(it)
            goalAdapter.notifyDataSetChanged()
        })

        goalAdapter = GoalAdapter(listGoal, this, itemViewModel, viewLifecycleOwner)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        val linearLayoutManager2 = LinearLayoutManager(requireContext())
        binding.apply {
            rcvGoalsCalendar.adapter = goalAdapter
            rcvGoalsCalendar.layoutManager = linearLayoutManager
            rcvGoalsCalendar.isNestedScrollingEnabled = false
            rcvGoalsCalendar2.adapter = goalAdapter
            rcvGoalsCalendar2.layoutManager = linearLayoutManager2
            rcvGoalsCalendar2.isNestedScrollingEnabled = false
        }
    }

    private fun jumpToDate() {
//        val dateDialog = Dialog
    }

    private fun setMonthView() {

        previousMonthAction(view)
        nextMonthAction(view)

        val currentDate = selectedDate.toString()
        daysInMonth = daysInMonthArray(selectedDate!!)
        calendarAdapter = CalendarAdapter(daysInMonth, this, currentDate)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 7)

        binding.apply {
            monthYearTV.text = monthYearFromDate(selectedDate!!)
            calendarRecyclerView.layoutManager = layoutManager
            calendarRecyclerView.adapter = calendarAdapter
        }
//        currentdate.text = selectedDate.toString()
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray: ArrayList<String> = ArrayList()
        val yearMonth: YearMonth = YearMonth.from(date)
        val daysInMonth: Int = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate!!.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                if (i > daysInMonth + dayOfWeek) break
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    private fun monthYearFromDate(date: LocalDate): String? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    private fun previousMonthAction(view: View?) {
        binding.previousMonth.setOnClickListener {
            selectedDate = selectedDate!!.minusMonths(1)
            setMonthView()
        }
    }

    private fun nextMonthAction(view: View?) {
        binding.nextMonth.setOnClickListener {
            selectedDate = selectedDate!!.plusMonths(1)
            setMonthView()
        }
    }

//    override fun onItemClick(position: Int, dayText: String?) {
//        if (!dayText.equals("")) {
//            val message =
//                "Selected Date " + dayText.toString() + " " + monthYearFromDate(selectedDate!!)
//            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//        } else {
//            Toast.makeText(this, "null", Toast.LENGTH_LONG).show()
//        }
//    }

    override fun onDateClicked(date: String, position: Int) {
        if (date != "") {
            val message =
                "Selected Date " + date.toString() + " " + monthYearFromDate(selectedDate!!)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "null", Toast.LENGTH_LONG).show()
        }
    }

    override fun goalItemClicked(goalModel: GoalModel) {
        TODO("Not yet implemented")
    }



}