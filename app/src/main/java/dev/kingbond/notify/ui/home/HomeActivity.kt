package dev.kingbond.notify.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityHomeBinding

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)


        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        homeBinding.fbAddNotify.setOnClickListener {
            // to show the three things -> events, task and goal
        }
    }


    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()

        // to close the drawer
        // drawerLayout.closeDrawers()
        // to set the title on top
        setTitle(title)

    }
}