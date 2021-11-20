package dev.kingbond.notify.ui.event

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import dev.kingbond.notify.R
import dev.kingbond.notify.alarmAndReminder.AlarmBrodcast
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.databinding.ActivityEventBinding
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_event.*
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EventActivity : AppCompatActivity() {

    private lateinit var eventbinding: ActivityEventBinding
    var distance: String = "0.0"

    //private lateinit var adapter: EventAdapter
    private lateinit var itemViewModel: ViewModelClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //coming from location search activity
        val startpoint = intent.getStringExtra("start")
        val startpointLatitude = intent.getStringExtra("startLatitude")
        val startpointLongitude = intent.getStringExtra("startLongitude")

        val endpoint = intent.getStringExtra("end")
        val endpointLatitude = intent.getStringExtra("endLatitude")
        val endpointLongitude = intent.getStringExtra("endLongitude")

        val eventType2 = intent.getStringExtra("eventType")
        val eventDescription2 = intent.getStringExtra("eventDescription")
        val eventDate2 = intent.getStringExtra("eventDate")
        val eventTime2 = intent.getStringExtra("eventTime")
        val eventTransport2 = intent.getStringExtra("eventTransport").toString()

        val startpoint2 = intent.getStringExtra("estart")
        val startpoint2Latitude = intent.getStringExtra("estartLatitude")
        val startpoint2Longitude = intent.getStringExtra("estartLongitude")


        distance = intent.getStringExtra("distance").toString()
        val time = intent.getStringExtra("time")

        eventbinding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(eventbinding.root)

        val roomDatabase = RoomDataBaseClass.getDataBaseObject(this)
        val dao = roomDatabase.getDao()
        val repo = RepositoryClass(dao)
        val viewModelFactory = ViewModelFactory(repo)
        itemViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ViewModelClass::class.java)


        /*Toast.makeText(
            this,
            "lat = $startpointLatitude lon = $startpointLongitude",
            Toast.LENGTH_SHORT
        )
            .show()*/


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


        //select type of transport
        val autotextViewTransport =
            findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewTypeOfTransport)
        // Get the array of languages
        val languagesTransport = resources.getStringArray(R.array.transport_types)
        // Create adapter and add in AutoCompleteTextView
        val adapterTransport = ArrayAdapter(
            this,
            R.layout.dropdown_item, languagesTransport
        )
        autotextViewTransport.setAdapter(adapterTransport)


        //add Date
        eventbinding.addEventDate.setOnClickListener {
            addDate()
        }


        //add Time
        eventbinding.addEventTime.setOnClickListener {
            addTime()
        }

        /*
        val eventType = eventbinding.autoCompleteTextViewTypeOfEvent.getText().toString()
        val eventDescription = eventbinding.etEventDescription.getText().toString()
        val eventDate = eventbinding.addEventDate.getText().toString()
        val eventTime = eventbinding.addEventTime.getText().toString()
        val eventTransport = eventbinding.autoCompleteTextViewTypeOfTransport.getText().toString()
         */

        //pick start point
        eventbinding.addStartPoint.setOnClickListener {

            val eventType3 = eventbinding.autoCompleteTextViewTypeOfEvent.getText().toString()
            val eventDescription3 = eventbinding.etEventDescription.getText().toString()
            val eventDate3 = eventbinding.addEventDate.getText().toString()
            val eventTime3 = eventbinding.addEventTime.getText().toString()
            val eventTransport3 =
                eventbinding.autoCompleteTextViewTypeOfTransport.getText().toString()

            val intent = Intent(this@EventActivity, LocationSearchActivity::class.java)
            intent.putExtra("location", "start")
            intent.putExtra("eventType", eventType3)
            intent.putExtra("eventDescription", eventDescription3)
            intent.putExtra("eventDate", eventDate3)
            intent.putExtra("eventTime", eventTime3)
            intent.putExtra("eventTransport", eventTransport3)
            startActivity(intent)
        }
        if (startpoint.toString() != "null") {
            eventbinding.addStartPoint.text = startpoint.toString()

            eventbinding.autoCompleteTextViewTypeOfEvent.setText(eventType2.toString())
            eventbinding.etEventDescription.setText(eventDescription2.toString())
            eventbinding.addEventDate.text = eventDate2.toString()
            eventbinding.addEventTime.text = eventTime2.toString()
            eventbinding.autoCompleteTextViewTypeOfTransport.setText(eventTransport2.toString())

        }


        //pick end point
        eventbinding.addEndPoint.setOnClickListener {

            val eventType3 = eventbinding.autoCompleteTextViewTypeOfEvent.getText().toString()
            val eventDescription3 = eventbinding.etEventDescription.getText().toString()
            val eventDate3 = eventbinding.addEventDate.getText().toString()
            val eventTime3 = eventbinding.addEventTime.getText().toString()
            val eventTransport3 =
                eventbinding.autoCompleteTextViewTypeOfTransport.getText().toString()

            val intent = Intent(this@EventActivity, LocationSearchActivity::class.java)
            intent.putExtra("location", "end")
            intent.putExtra("start", eventbinding.addStartPoint.text.toString())
            intent.putExtra("startLatitude", startpointLatitude.toString())
            intent.putExtra("startLongitude", startpointLongitude.toString())
            intent.putExtra("eventType", eventType3)
            intent.putExtra("eventDescription", eventDescription3)
            intent.putExtra("eventDate", eventDate3)
            intent.putExtra("eventTime", eventTime3)
            intent.putExtra("eventTransport", eventTransport3)
            startActivity(intent)
        }
        if (endpoint.toString() != "null") {
            eventbinding.addEndPoint.text = endpoint.toString()

            eventbinding.addStartPoint.text = startpoint2.toString()

            eventbinding.autoCompleteTextViewTypeOfEvent.setText(eventType2.toString())
            eventbinding.etEventDescription.setText(eventDescription2.toString())
            eventbinding.addEventDate.text = eventDate2.toString()
            eventbinding.addEventTime.text = eventTime2.toString()
            eventbinding.autoCompleteTextViewTypeOfTransport.setText(eventTransport2.toString())

        }


        //distance
        if (distance != "null" && distance != "0.0 KM") {
            eventbinding.tvDistance.text = distance.toString() + " KM"
            eventbinding.tvTimeAlarm.text = time.toString()
        }


        //add to db
        eventbinding.btnAddEventFab.setOnClickListener(View.OnClickListener
        {
            val eventType = eventbinding.autoCompleteTextViewTypeOfEvent.getText().toString()
            val eventDescription = etEventDescription.getText().toString()
            val eventDate = addEventDate.getText().toString()
            val eventTime = addEventTime.getText().toString()
            val eventTransport =
                eventbinding.autoCompleteTextViewTypeOfTransport.getText().toString()


            Toast.makeText(
                this,
                (eventTransport + " " + time + " " + startpoint2Latitude + " " + startpoint2Longitude),
                Toast.LENGTH_SHORT
            ).show()


            if (startpoint2 != null && startpoint2Latitude != null && startpoint2Longitude != null && endpoint != null && endpointLatitude != null && endpointLongitude != null && distance != null && time != null) {
                val eventModel =
                    EventModel(
                        eventType,
                        eventDescription,
                        eventDate,
                        eventTime,
                        eventTransport,
                        startpoint2,
                        startpoint2Latitude,
                        startpoint2Longitude,
                        endpoint,
                        endpointLatitude,
                        endpointLongitude,
                        distance,
                        time,
                        time
                    )
                itemViewModel.insertDataIntoEventTable(eventModel)
            }

            //set alarm
            setAlarm(
                (eventDescription + " " + eventType),
                eventDate,
                time.toString()
            )


            val intent = Intent(this, EventHomeActivity::class.java)
            startActivity(intent)
        })
    }


    private fun addTime() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            addEventTime.text = SimpleDateFormat("hh:mm aaa").format(cal.time)
        }

        TimePickerDialog(
            this,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            false
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

    private fun setAlarm(text: String, date: String, time: String) {
        val am = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(applicationContext, AlarmBrodcast::class.java)
        intent.putExtra("event", text)
        intent.putExtra("date", date)
        intent.putExtra("time", time)
        val pendingIntent =
            PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val dateandtime = "$date $time"
        val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd hh-mm aaa")
        try {
            val date1 = formatter.parse(dateandtime)
            am[AlarmManager.RTC_WAKEUP, date1.time] = pendingIntent
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

}