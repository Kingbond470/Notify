package dev.kingbond.notify.ui.task

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import dev.kingbond.notify.R
import dev.kingbond.notify.alarmAndReminder.AlarmBrodcast
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.databinding.ActivityTaskBinding
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.ui.home.HomeActivity
import dev.kingbond.notify.ui.task.model.TaskModel
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TaskActivity : AppCompatActivity() {

    private lateinit var binding:ActivityTaskBinding
    private lateinit var itemViewModel:ViewModelClass

    private  var timeNotify = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomDatabase = RoomDataBaseClass.getDataBaseObject(this)
        val dao = roomDatabase.getDao()
        val repo = RepositoryClass(dao)
        val viewModelFactory = ViewModelFactory(repo)
        itemViewModel = ViewModelProviders.of(this,viewModelFactory).get(ViewModelClass::class.java)

        binding.ibProfileBackNewTask.setOnClickListener {
            val intent = Intent(this, TaskHomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        setDate()
        setTime()
        submitItemTask()
    }

    private fun submitItemTask() {
        binding.submitTask.setOnClickListener {

            binding.apply {
                val taskModel = TaskModel(
                    TaskName.text.toString(),
                    TaskDescription.text.toString(),
                    addToDateTask.text.toString(),
                    addTimeTask.text.toString(),
                    0,
                    "Normal")

                itemViewModel.insertDataInTaskTable(taskModel)
                setAlarm(TaskDescription.text.toString(),addToDateTask.text.toString().trim(),addTimeTask.text.toString())

            }

//            val intent = Intent(this,TaskHomeActivity::class.java)
//            startActivity(intent)
            finish()
        }
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

                    timeNotify = "$i:$i2"
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