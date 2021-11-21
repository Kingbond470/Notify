package dev.kingbond.notify.ui.event

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kingbond.notify.data.database.RoomDataBaseClass
import dev.kingbond.notify.databinding.ActivityEventHomeBinding
import dev.kingbond.notify.repository.RepositoryClass
import dev.kingbond.notify.ui.home.HomeActivity
import dev.kingbond.notify.viewmodel.ViewModelClass
import dev.kingbond.notify.viewmodel.ViewModelFactory
import java.io.Serializable

class EventHomeActivity : AppCompatActivity(), EventClickListener {

    private lateinit var binding: ActivityEventHomeBinding
    private lateinit var adapter: EventAdapter
    private lateinit var itemViewModel: ViewModelClass
    private var list = arrayListOf<EventModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackEvents.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }

        val roomDatabase = RoomDataBaseClass.getDataBaseObject(this)
        val dao = roomDatabase.getDao()
        val repo = RepositoryClass(dao)
        val viewModelFactory = ViewModelFactory(repo)
        itemViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ViewModelClass::class.java)


        itemViewModel.getDataFromEventTable().observe(this, androidx.lifecycle.Observer {
            list.clear()
            list.addAll(it)
            list.reverse()
            adapter.notifyDataSetChanged()
        })

        setRecyclerView()
        binding.addEventHome.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setRecyclerView() {
        adapter = EventAdapter(list, this)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.apply {
            eventRecyclerView.adapter = adapter
            eventRecyclerView.layoutManager = linearLayoutManager
        }
    }

    override fun eventItemClicked(eventModel: EventModel) {
        val intent = Intent(this, EventDetailsActivity::class.java)
        intent.putExtra("eventModel", eventModel as Serializable)
        startActivity(intent)
    }

}