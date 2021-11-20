package dev.kingbond.notify.ui.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ItemEventLayoutBinding

class EventAdapter(
    private val list: ArrayList<EventModel>,
    val eventClickListener: EventClickListener
) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_event_layout,
                parent,
                false
            ), eventClickListener
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = list[position]

        /*
        var t: String = list[position].counter_time.toString()
        while (t <= 0) {
            t--
        }

        */

        holder.setEventData(event)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class EventViewHolder(
    var itemEventLayoutBinding: ItemEventLayoutBinding,
    val eventClickListener: EventClickListener
) : RecyclerView.ViewHolder(itemEventLayoutBinding.root) {

    fun setEventData(eventModel: EventModel) {
        itemEventLayoutBinding.event = eventModel
        itemEventLayoutBinding.eventItem.setOnClickListener {
            eventClickListener.eventItemClicked(eventModel)
        }
    }

}