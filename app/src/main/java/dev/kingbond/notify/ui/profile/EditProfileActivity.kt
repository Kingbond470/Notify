package dev.kingbond.notify.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ActivityEditProfileBinding
import dev.kingbond.notify.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var editProfileBinding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        editProfileBinding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(editProfileBinding.root)

        //back button
        editProfileBinding.ibProfileBack.setOnClickListener {
            val intent = Intent(baseContext, HomeActivity::class.java)
            startActivity(intent)
        }

        editProfileBinding.btnSave.setOnClickListener {
            Toast.makeText(baseContext, "Data Saved Successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(baseContext, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        editProfileBinding.radioMale.setOnClickListener {
            ivProfileImage.setImageResource(R.drawable.man)
        }

        editProfileBinding.radioFemale.setOnClickListener {
            ivProfileImage.setImageResource(R.drawable.woman)
        }

        editProfileBinding.radioOthers.setOnClickListener {
            ivProfileImage.setImageResource(R.drawable.other)
        }

    }
}