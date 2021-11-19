package dev.kingbond.notify.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ActivityHomeBinding
import dev.kingbond.notify.ui.about.AboutActivity
import dev.kingbond.notify.ui.profile.EditProfileActivity
import dev.kingbond.notify.ui.settings.SettingsActivity

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

        // to access the view from header in Navigation View
        val header: View = navView.getHeaderView(0)
        val ibEdit: ImageButton = header.findViewById(R.id.ibEditProfile)
        //val ivImage:CircleImageView=header.findViewById(R.id.ivProfileImageHeaderLayout)
        ibEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }



        homeBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    // replaceFragment()
                    Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.calendarFragment -> {
                    // replaceFragment()
                    Toast.makeText(applicationContext, "Calendar", Toast.LENGTH_SHORT).show()
                }
                R.id.completedFragment -> {
                    // replaceFragment()
                    Toast.makeText(applicationContext, "Completerd", Toast.LENGTH_SHORT).show()
                }
                R.id.profileFragment -> {
                    // replaceFragment()
                    Toast.makeText(applicationContext, "Profile", Toast.LENGTH_SHORT).show()
                }

            }
            true
        }


        //on click navigation item - menu drawer
        homeBinding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_task -> {
//                    val intent =
//                        Intent(this@HomeActivity, Task::class.java)
//                    startActivity(intent)
//                    drawableLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(this, "Task", Toast.LENGTH_SHORT).show()
                }


                // to open a new fragment which is health files
                R.id.nav_completed -> {
//                    supportFragmentManager.beginTransaction().apply {
//                        replace(R.id.fragmentContainer, CompletedFragment())
//                            .commit()
//                    }
                    Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show()

                }

                R.id.nav_about -> {
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                }

//                R.id.privacyPolicy -> {
//                    Toast.makeText(
//                        applicationContext,
//                        "Privacy Policy | Work in Process",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }

                R.id.nav_settings -> {
                    Toast.makeText(
                        applicationContext,
                        "Settings",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent =
                        Intent(this@HomeActivity, SettingsActivity::class.java)
                    startActivity(intent)
                    homeBinding.drawerLayout.closeDrawer(GravityCompat.START)

                }

            }
            true
        })


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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true

        return super.onOptionsItemSelected(item)
    }
}