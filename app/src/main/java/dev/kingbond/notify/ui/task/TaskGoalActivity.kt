package dev.kingbond.notify.ui.task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.databinding.ActivityTaskGoalBinding
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.ui.home.HomeActivity
import dev.kingbond.notify.ui.task.model.TaskModel
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class TaskGoalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskGoalBinding
    private lateinit var itemViewModel: ViewModelClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val goalName = intent.getStringExtra("goalName")
        val roomDatabase = RoomDataBaseClass.getDataBaseObject(this)
        val dao = roomDatabase.getDao()
        val repo = RepositoryClass(dao)
        val viewModelFactory = ViewModelFactory(repo)
        itemViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ViewModelClass::class.java)


        binding.ibProfileBackNewGoal.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        setDate()
        setTime()
        submitItemTask(goalName!!)
    }

    private fun submitItemTask(goalName: String) {
        binding.submitTask.setOnClickListener {

            binding.apply {
                val taskModel = TaskModel(
                    TaskName.text.toString(),
                    TaskDescription.text.toString(),
                    addToDateTask.text.toString(), addTimeTask.text.toString(),
                    0,
                    goalName
                )

                itemViewModel.insertDataInTaskTable(taskModel)
            }

//            val intent = Intent(this,GoalHomeActivity::class.java)
//            startActivity(intent)
            finish()
        }
    }

    private fun setTime() {
        val sdf = SimpleDateFormat("hh-mm aaa")
        val currentDate = sdf.format(Date())
        binding.addTimeTask.text = currentDate

        binding.addTimeTask.setOnClickListener {
            val cal = Calendar.getInstance()

            val dateSetListener =
                TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                    cal.set(Calendar.HOUR_OF_DAY, i)
                    cal.set(Calendar.MINUTE, i2)

                    val myFormat = "hh-mm aaa" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat)
                    binding.addTimeTask.text = sdf.format(cal.time)
                }

            TimePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.HOUR),
                cal.get(Calendar.MINUTE),
                false
            ).show()

        }
    }

    private fun setDate() {
        val sdf = SimpleDateFormat("  yyyy-MM-dd")
        val currentDate = sdf.format(Date())
        binding.addToDateTask.text = currentDate

        binding.addToDateTask.setOnClickListener {
            val cal = Calendar.getInstance()

            val dateSetListener =
                DatePickerDialog.OnDateSetListener { anotherView, year, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val myFormat = "yyyy-MM-dd" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    binding.addToDateTask.text = sdf.format(cal.time)
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
}