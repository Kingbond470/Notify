package dev.kingbond.notify.ui.event

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import dev.kingbond.notify.MainActivity
import dev.kingbond.notify.R
import kotlinx.android.synthetic.main.activity_event.*
import java.text.SimpleDateFormat
import java.util.*

class EventActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityLoginBinding

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        //binding = ActivityLoginBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        val autotextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewTypeOfEvent)
        // Get the array of languages
        val languages = resources.getStringArray(R.array.event_types)
        // Create adapter and add in AutoCompleteTextView
        val adapter = ArrayAdapter(
            this,
            R.layout.dropdown_item, languages
        )
        autotextView.setAdapter(adapter)


        addEventDate.setOnClickListener {
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


        addEventTime.setOnClickListener {
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



        addStartPoint.setOnClickListener {
            val intent = Intent(this@EventActivity, LocationSearchActivity::class.java)
            startActivity(intent)
        }

        addEndPoint.setOnClickListener {
            val intent = Intent(this@EventActivity, LocationSearchActivity::class.java)
            startActivity(intent)
        }




        btnAddEventFab.setOnClickListener(View.OnClickListener
        {
            val eventType = autotextView.getText().toString()
            val eventDescription = etEventDescription.getText()
            val eventDate = addEventDate.getText()
            val eventTime = addEventTime.getText()
            Toast.makeText(
                this,
                (eventType + " " + eventDescription + " " + eventDate + " " + eventTime),
                Toast.LENGTH_SHORT
            ).show()
        })
    }

}