package dev.kingbond.notify.ui.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kingbond.notify.databinding.ActivityEventDetailsBinding

class EventDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEventDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getSerializableExtra("eventModel") as EventModel
        binding.apply {
            eventDetailsTitle.text = data.type
            eventDetailsDesc.text = data.event_description
            eventDetailsEventDate.text = data.date
            eventDetailsEventTime.text = data.event_time
            eventDetailsEventVehicle.text = data.transport_type
            tvEventDetailFrom.text = data.start_point
            tvEventDetailTo.text = data.end_point
            eventDetailsEventDistance.text = data.distance
            eventDetailsEventTimeLeft.text = data.counter_time
        }


    }

}