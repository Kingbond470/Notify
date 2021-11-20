package dev.kingbond.notify.ui.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ItemEventLayoutBinding

class EventAdapter(
    private val list: ArrayList<EventModel>
) : RecyclerView.Adapter<EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_event_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = list[position]
        holder.setGoalData(event)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class EventViewHolder(
    var itemEventLayoutBinding: ItemEventLayoutBinding
) : RecyclerView.ViewHolder(itemEventLayoutBinding.root) {

    fun setGoalData(eventModel: EventModel) {
        itemEventLayoutBinding.event = eventModel
    }
}