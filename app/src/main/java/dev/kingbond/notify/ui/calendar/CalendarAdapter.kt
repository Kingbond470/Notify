package dev.kingbond.notify.ui.calendar

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.notify.R
import kotlinx.android.synthetic.main.calendar_cell.view.*
import java.time.LocalDate
import java.util.*

//@RequiresApi(Build.VERSION_CODES.O)
//class CalendarAdapter(
//    private val daysOfMonth: ArrayList<String>,
//    private val onItemListener: OnItemListener
//) :
//    RecyclerView.Adapter<CalendarViewHolder>() {
//
//
//    private var selectedDate: LocalDate? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val view: View = inflater.inflate(R.layout.calendar_cell, parent, false)
////        val layoutParams = view.layoutParams
////        layoutParams.height = (parent.height * 0.166666666).toInt()
//        return CalendarViewHolder(view, onItemListener)
//    }
//
//    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
//        holder.dayOfMonth.text = daysOfMonth[position]
//
//        selectedDate = LocalDate.now()
//        val date = selectedDate.toString().split("-")
//        if (date[2] == daysOfMonth[position]) {
////            holder.itemView.setBackgroundColor(Color.parseColor("#000000"))
//            holder.itemView.rlDate.setBackgroundResource(R.drawable.current_day_bg)
//            holder.itemView.cellDayText.setTextColor(Color.parseColor("#FFFFFFFF"))
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        return daysOfMonth.size
//    }
//
//    interface OnItemListener {
//        fun onItemClick(position: Int, dayText: String?)
//    }
//}


@RequiresApi(Build.VERSION_CODES.O)
class CalendarAdapter(
    private var dateList: ArrayList<String>,
    private val clickListener: DateClickListener,
    private val currentDate: String
) :
    RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    val bool = BooleanArray(dateList.size)
    private val itemViewList = ArrayList<View>()
    private var selectedDate: LocalDate? = null
    private var curDate: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_cell, parent, false)
        itemViewList.add(view)
        return CalendarViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = dateList[position]
        holder.setData(date)
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

    inner class CalendarViewHolder(
        private val view: View,
        private val clickListener: DateClickListener
    ) : RecyclerView.ViewHolder(view) {

        internal fun setData(date: String) {
            view.apply {

                cellDayText.text = date

//                view.setOnClickListener {
//                    clickListener.onDateClicked(date, adapterPosition)
//                }

                selectedDate = LocalDate.now()
                curDate = selectedDate.toString().split("-")

                val ar = currentDate.split("-")

                if (curDate!![2] == dateList[adapterPosition] && curDate!![0] == ar[0] && curDate!![1] == ar[1]) {
                    bool[adapterPosition] = true
                    view.rlDate.setBackgroundResource(R.drawable.current_day_bg)
                    view.cellDayText.setTextColor(Color.parseColor("#FFFFFFFF"))
                }

                if (view.cellDayText.text.toString() != "") {

                    eventsCardView.visibility = View.VISIBLE

                    rlDate.setOnClickListener {
                        if (bool[adapterPosition]) {
                            if (date == curDate!![2]) {
                                view.rlDate.setBackgroundResource(R.drawable.current_day_bg)
                                view.cellDayText.setTextColor(Color.parseColor("#FFFFFFFF"))
                            }
//                        rlDate.setBackgroundResource(R.drawable.selected_date_bg)
                        } else {
                            check()
                            if (date == curDate!![2]) {
                                view.rlDate.setBackgroundResource(R.drawable.current_day_bg)
                                view.cellDayText.setTextColor(Color.parseColor("#FFFFFFFF"))
                            } else {
                                rlDate.setBackgroundResource(R.drawable.selected_date_bg)
                            }
                            bool[adapterPosition] = true
                        }

                        clickListener.onDateClicked(date, adapterPosition)

                    }
                }
            }
        }

        private fun check() {
            for (i in 0 until dateList.size) {
                if (bool[i] && i != adapterPosition) {
                    if (itemViewList[i].cellDayText.text.toString() == curDate!![2]) {
                        itemViewList[i].cellDayText.setTextColor(Color.parseColor("#527FF3"))
                        itemViewList[i].rlDate.setBackgroundResource(0)
                    } else {
                        itemViewList[i].rlDate.setBackgroundResource(0)
                    }
                    bool[i] = false
//                    itemViewList[i].labourLayout.visibility = View.GONE
                }
            }

        }

    }
}

interface DateClickListener {
    fun onDateClicked(date: String, position: Int)
}
