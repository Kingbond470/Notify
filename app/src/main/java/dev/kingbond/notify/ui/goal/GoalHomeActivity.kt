package dev.kingbond.notify.ui.goal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ActivityGoalHomeBinding

class GoalHomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGoalHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addGoalHome.setOnClickListener {
            val intent = Intent(this,GoalActivity::class.java)
            startActivity(intent)
        }
    }
}