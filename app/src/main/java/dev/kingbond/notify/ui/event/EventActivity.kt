package dev.kingbond.notify.ui.event

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import dev.kingbond.notify.R
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.databinding.ActivityEventBinding
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.ui.goal.GoalHomeActivity
import dev.kingbond.notify.ui.goal.model.GoalModel
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_event.*
import java.text.SimpleDateFormat
import java.util.*

class EventActivity : AppCompatActivity() {

    private lateinit var eventbinding: ActivityEventBinding

    //private lateinit var adapter: GoalAdapter
    private lateinit var itemViewModel: ViewModelClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        eventbinding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(eventbinding.root)

        val roomDatabase = RoomDataBaseClass.getDataBaseObject(this)
        val dao = roomDatabase.getDao()
        val repo = RepositoryClass(dao)
        val viewModelFactory = ViewModelFactory(repo)
        itemViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ViewModelClass::class.java)

        //select type of event
        val autotextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewTypeOfEvent)
        // Get the array of languages
        val languages = resources.getStringArray(R.array.event_types)
        // Create adapter and add in AutoCompleteTextView
        val adapter = ArrayAdapter(
            this,
            R.layout.dropdown_item, languages
        )
        autotextView.setAdapter(adapter)

        //add Date
        eventbinding.addEventDate.setOnClickListener {
            addDate()
        }

        //add Time
        eventbinding.addEventTime.setOnClickListener {
            addTime()
        }

        //pick start point
        eventbinding.addStartPoint.setOnClickListener {
            val intent = Intent(this@EventActivity, LocationSearchActivity::class.java)
            startActivity(intent)
        }

        //pick end point
        eventbinding.addEndPoint.setOnClickListener {
            val intent = Intent(this@EventActivity, LocationSearchActivity::class.java)
            startActivity(intent)
        }

        //add to db
        eventbinding.btnAddEventFab.setOnClickListener(View.OnClickListener
        {
            addEvent()
        })
    }

    private fun addEvent() {
        val eventType = eventbinding.autoCompleteTextViewTypeOfEvent.getText().toString()
        val eventDescription = etEventDescription.getText().toString()
        val eventDate = addEventDate.getText().toString()
        val eventTime = addEventTime.getText().toString()


        Toast.makeText(
            this,
            (eventType + " " + eventDescription + " " + eventDate + " " + eventTime),
            Toast.LENGTH_SHORT
        ).show()


        val eventModel = EventModel(eventType, eventDescription, eventDate, eventTime)
        itemViewModel.insertDataIntoEventTable(eventModel)

        val intent = Intent(this, EventHomeActivity::class.java)
        startActivity(intent)

    }

    private fun addTime() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            addEventTime.text = SimpleDateFormat("HH:mm").format(cal.time)
        }

        TimePickerDialog(
            this,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }

    private fun addDate() {
        val cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { anotherView, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "yyyy-MM-dd" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                addEventDate.text = sdf.format(cal.time)
            }

        DatePickerDialog(
            this,
            dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

}