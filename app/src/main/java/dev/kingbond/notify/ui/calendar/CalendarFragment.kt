package dev.kingbond.notify.ui.calendar

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.notify.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class CalendarFragment : Fragment(R.layout.fragment_calendar), DateClickListener {

    private var selectedDate: LocalDate? = null
    private lateinit var calendarAdapter: CalendarAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedDate = LocalDate.now()
        setMonthView()
        jumpToDate()
    }

    private fun jumpToDate() {
//        val dateDialog = Dialog
    }

    private fun setMonthView() {

        previousMonthAction(view)
        nextMonthAction(view)

        val currentDate = selectedDate.toString()
        monthYearTV.text = monthYearFromDate(selectedDate!!)
        val daysInMonth: ArrayList<String> = daysInMonthArray(selectedDate!!)
        calendarAdapter = CalendarAdapter(daysInMonth, this, currentDate)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 7)
        calendarRecyclerView.layoutManager = layoutManager
        calendarRecyclerView.adapter = calendarAdapter
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
//                if(i > daysInMonth + dayOfWeek) {
//                    daysInMonthArray.add(((daysInMonth + dayOfWeek)%daysInMonth).toString())
//                } else {
//                    daysInMonthArray.add((daysInMonth + dayOfWeek).toString())
//                }
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
        previousMonth.setOnClickListener {
            selectedDate = selectedDate!!.minusMonths(1)
            setMonthView()
        }
    }

    private fun nextMonthAction(view: View?) {
        nextMonth.setOnClickListener {
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
        if (!date.equals("")) {
            val message =
                "Selected Date " + date.toString() + " " + monthYearFromDate(selectedDate!!)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "null", Toast.LENGTH_LONG).show()
        }
    }

}