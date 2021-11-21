package dev.kingbond.notify.ui.goal

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ActivityGoalBinding
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.ui.goal.model.GoalModel
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class GoalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoalBinding
    private lateinit var itemViewModel: ViewModelClass

    private  var timeNotify = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_goal)

        val roomDatabase = RoomDataBaseClass.getDataBaseObject(this)
        val dao = roomDatabase.getDao()
        val repo = RepositoryClass(dao)
        val viewModelFactory = ViewModelFactory(repo)
        itemViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ViewModelClass::class.java)



        addDateTo()
        addDateFrom()
        setTimeTo()
        setTimeFrom()
        submitTheGoal()

    }


    private fun submitTheGoal() {
        binding.submitGoal.setOnClickListener {

            val name = binding.goalTitle.text.toString()
            val desc = binding.goalDescription.text.toString()
            val toDate = binding.addToDateGoal.text.toString()
            val fromDate = binding.addFromDateGoal.text.toString()

            val goalModel = GoalModel(name, desc, toDate, fromDate,0)
            itemViewModel.insertDataIntoGoalTable(goalModel)

//            val intent = Intent(this, GoalHomeActivity::class.java)
//            startActivity(intent)
            finish()
        }
    }

    private fun addDateFrom() {
        val sdf = SimpleDateFormat("  yyyy-MM-dd")
        val currentDate = sdf.format(Date())
        binding.addFromDateGoal.text = currentDate

        binding.addFromDateGoal.setOnClickListener {
            val cal = Calendar.getInstance()

            val dateSetListener =
                DatePickerDialog.OnDateSetListener { anotherView, year, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val myFormat = "yyyy-MM-dd" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    binding.addFromDateGoal.text = sdf.format(cal.time)
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

    private fun addDateTo() {
        val sdf = SimpleDateFormat("  yyyy-MM-dd")
        val currentDate = sdf.format(Date())
        binding.addToDateGoal.text = currentDate

        binding.addToDateGoal.setOnClickListener {
            val cal = Calendar.getInstance()

            val dateSetListener =
                DatePickerDialog.OnDateSetListener { anotherView, year, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val myFormat = "yyyy-MM-dd" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    binding.addToDateGoal.text = sdf.format(cal.time)
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

    private fun setTimeTo() {
        val sdf = SimpleDateFormat("hh-mm aaa")
        val currentDate = sdf.format(Date())
        binding.addToTimeGoal.text = currentDate

        binding.addToTimeGoal.setOnClickListener {
            val cal = Calendar.getInstance()

            val dateSetListener =
                TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                    cal.set(Calendar.HOUR_OF_DAY, i)
                    cal.set(Calendar.MINUTE, i2)

                    timeNotify = "$i:$i2"
                    val myFormat = "hh-mm aaa" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat)
                    binding.addToTimeGoal.text = sdf.format(cal.time)
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

    private fun setTimeFrom() {
        val sdf = SimpleDateFormat("hh-mm aaa")
        val currentDate = sdf.format(Date())
        binding.addTimeFromGoal.text = currentDate

        binding.addTimeFromGoal.setOnClickListener {
            val cal = Calendar.getInstance()

            val dateSetListener =
                TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                    cal.set(Calendar.HOUR_OF_DAY, i)
                    cal.set(Calendar.MINUTE, i2)

                    timeNotify = "$i:$i2"
                    val myFormat = "hh-mm aaa" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat)
                    binding.addTimeFromGoal.text = sdf.format(cal.time)
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
}