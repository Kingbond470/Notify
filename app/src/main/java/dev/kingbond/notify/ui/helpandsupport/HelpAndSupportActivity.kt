package dev.kingbond.notify.ui.helpandsupport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ActivityHelpAndSupportBinding
import dev.kingbond.notify.ui.home.HomeActivity

class HelpAndSupportActivity : AppCompatActivity() {

    private lateinit var helpAndSupportBinding: ActivityHelpAndSupportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        helpAndSupportBinding = ActivityHelpAndSupportBinding.inflate(layoutInflater)
        setContentView(helpAndSupportBinding.root)

        helpAndSupportBinding.ibProfileBackHelp.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}