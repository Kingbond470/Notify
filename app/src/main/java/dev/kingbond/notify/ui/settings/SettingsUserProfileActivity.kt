package dev.kingbond.notify.ui.settings

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.ActivitySettingsUserProfileBinding
import dev.kingbond.notify.ui.login.LoginActivity

class SettingsUserProfileActivity : AppCompatActivity() {

    private lateinit var mAuth:FirebaseAuth
    private lateinit var settingUserProfileActivityBinding:ActivitySettingsUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingUserProfileActivityBinding=ActivitySettingsUserProfileBinding.inflate(layoutInflater)
        setContentView(settingUserProfileActivityBinding.root)

        //profile
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        if (user != null) {
            Glide.with(settingUserProfileActivityBinding.ivEditProfileImage).load(user.photoUrl)
                .into(settingUserProfileActivityBinding.ivEditProfileImage)
            settingUserProfileActivityBinding.tvEditProfileName.text = user.displayName
        }

        settingUserProfileActivityBinding.tvEditLogout.setOnClickListener {
            mAuth.signOut()
            val logout = Intent(this, LoginActivity::class.java)
            startActivity(logout)
            //(activity as Activity?)?.overridePendingTransition(0, 0)
        }
    }
}