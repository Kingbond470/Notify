package dev.kingbond.notify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kingbond.notify.ui.event.EventActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnEvent.setOnClickListener {
            val intent = Intent(this@MainActivity, EventActivity::class.java)
            startActivity(intent)
        }
    }
}