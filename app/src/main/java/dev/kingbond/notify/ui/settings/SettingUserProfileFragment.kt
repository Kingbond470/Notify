package dev.kingbond.notify.ui.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import dev.kingbond.notify.R
import dev.kingbond.notify.databinding.FragmentSettingUserProfileBinding
import dev.kingbond.notify.ui.login.LoginActivity

class SettingUserProfileFragment : Fragment(R.layout.fragment_setting_user_profile) {

    //profile
    private lateinit var mAuth: FirebaseAuth

    private lateinit var settingUserProfileBinding: FragmentSettingUserProfileBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingUserProfileBinding = FragmentSettingUserProfileBinding.bind(view)

        //profile
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        if (user != null) {
            Glide.with(settingUserProfileBinding.ivEditProfileImage).load(user.photoUrl)
                .into(settingUserProfileBinding.ivEditProfileImage)
            settingUserProfileBinding.tvEditProfileName.text = user.displayName
        }

        settingUserProfileBinding.tvEditLogout.setOnClickListener {
            mAuth.signOut()
            val logout = Intent(activity, LoginActivity::class.java)
            startActivity(logout)
            (activity as Activity?)?.overridePendingTransition(0, 0)
        }

    }

}