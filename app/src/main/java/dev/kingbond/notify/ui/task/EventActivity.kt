package dev.kingbond.notify.ui.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import dev.kingbond.notify.R

class EventActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        //binding = ActivityLoginBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        // get reference to the string array that we just created
        val events = resources.getStringArray(R.array.event_types)
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, events)
        // get reference to the autocomplete text view
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewTypeOfEvent)
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter)

        val selectedEvent = autocompleteTV.selected
    }
}