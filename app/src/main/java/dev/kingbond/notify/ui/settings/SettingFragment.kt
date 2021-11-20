package dev.kingbond.notify.ui.settings

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil.setContentView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import dev.kingbond.notify.databinding.ActivitySettingsBinding
import dev.kingbond.notify.databinding.FragmentSettingBinding
import dev.kingbond.notify.ui.home.HomeActivity
import dev.kingbond.notify.ui.login.LoginActivity
import android.R
import androidx.fragment.app.FragmentTransaction


class SettingFragment
    : Fragment(dev.kingbond.notify.R.layout.fragment_setting) {
    private lateinit var settingsBinding: FragmentSettingBinding
    //profile
    private lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            settingsBinding = FragmentSettingBinding.bind(view)

            settingsBinding.llViewProfile.setOnClickListener {
               // startActivity(Intent(requireContext(), SettingsUserProfileActivity::class.java))
                val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
                ft.replace(dev.kingbond.notify.R.id.fragmentContainer, SettingUserProfileFragment(), "User Profile Fragment")
                ft.addToBackStack(null)
                ft.commit()
            }


            //profile
            mAuth = FirebaseAuth.getInstance()
            val user = mAuth.currentUser
            if (user != null) {
                Glide.with(settingsBinding.ivSettingProfileImage).load(user.photoUrl)
                    .into(settingsBinding.ivSettingProfileImage)
                settingsBinding.tvSettingsProfileName.text = user.displayName
                settingsBinding.ivEmail.text = user.email
                settingsBinding.tvLogOutName.text = "You are logged in as ${user.displayName}"
                Glide.with(settingsBinding.ivSettingProfileImage).load(user.photoUrl)
                    .into(settingsBinding.ivSettingProfileImage)
                settingsBinding.tvSettingsProfileName.text = user.displayName
                settingsBinding.ivEmail.text = user.email
                settingsBinding.tvLogOutName.text = "You are logged in as ${user.displayName}"
            } else {
                Glide.with(settingsBinding.ivSettingProfileImage).load(dev.kingbond.notify.R.drawable.man)
                    .into(settingsBinding.ivSettingProfileImage)
                settingsBinding.tvSettingsProfileName.text = "Masai Android"
            }

            // back to mainactivity
//        cdSettingsProfile.setOnClickListener {
//            val intent = Intent(context, MainActivity::class.java)
//            startActivity(intent)
//        }

            //logout
            settingsBinding.tvLogOut.setOnClickListener {
                mAuth.signOut()
                val logout = Intent(requireContext(), LoginActivity::class.java)
                startActivity(logout)

                //  (activity as Activity?)?.overridePendingTransition(0, 0)
            }

            if (settingsBinding.spinnerSettingDownload.count > 1) {
                settingsBinding.spinnerSettingDownload.setSelection(1)
            }

            settingsBinding.seekbarSettingCrossfade.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        if (progress >= 0 && progress <= seekBar.max) {
                            val progressString = (progress).toString() + " s"
                            settingsBinding.tvCrossfade12s.text =
                                progressString // the TextView Reference
                            seekBar.secondaryProgress = progress
                        }
                    }
                }
            })

            //for storage
            val statFs = StatFs(Environment.getDataDirectory().absolutePath);
            var total = (statFs.blockCount.toFloat() * statFs.blockSize) / 1048576;
            var free = (statFs.availableBlocks.toFloat() * statFs.blockSize) / 1048576;
            total /= 1024
            free /= 1024
            total = String.format("%.1f", total).toFloat()
            free = String.format("%.1f", free).toFloat()

            val busy = String.format("%.1f", (total - free)).toFloat()

            settingsBinding.indicator.max = total.toInt()
            settingsBinding.indicator.progress = busy.toInt()
            settingsBinding.tvFreeSpace.text = "$free GB"
            settingsBinding.tvUsedSpace.text = "$busy GB"

//            val cache = cacheDir.toString()
//            settingsBinding.tvCacheSpace.text = "2.2 MB"
//
//            settingsBinding.llDeleteCache.setOnClickListener {
//                cacheDir?.deleteRecursively()
//                settingsBinding.rlCache.visibility = View.GONE
//            }

        }



    }

