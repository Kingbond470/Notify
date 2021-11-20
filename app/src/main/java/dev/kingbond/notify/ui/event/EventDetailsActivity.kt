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
            goalDetailsTitle.text = data.type
            goalDetailsDesc.text = data.event_description
            toGoalDetails.text = data.date
            fromGoalDetails.text = data.event_time
        }


    }

}