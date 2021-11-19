package dev.kingbond.notify.ui.splash

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dev.kingbond.notify.MainActivity
import dev.kingbond.notify.R
import java.io.File
import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnFailureListener

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import dev.kingbond.notify.ui.login.LoginActivity

class SpalshActivity : AppCompatActivity() {

    // private lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)

        //setting product image in detailed view
//        storageReference =
//            FirebaseStorage.getInstance().reference.child("splash/" + id + ".jpg")
//        val localFile = File.createTempFile("tempImage", "jpg")
//        storageReference.getFile(localFile).addOnSuccessListener {
//            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
//           // ivDetailProductImage.setImageBitmap(bitmap)
//        }

//        val videoRef: StorageReference =
//            storageReference.child("/splash/" + userUid.toString() + "/" + notify_splash)
//
//        val ONE_MEGABYTE = (1024 * 1024).toLong()
//        videoRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
//            // Transform bytes to a video, play
//        }.addOnFailureListener {
//            // Handle any errors
//        }

        try {
            val videoHolder = VideoView(this)
            setContentView(videoHolder)

            val video: Uri =
                Uri.parse("android.resource://" + packageName + "/" + R.raw.notify_splash)
            videoHolder.scaleX
            videoHolder.setVideoURI(video)
            videoHolder.setOnCompletionListener { jump() }
            videoHolder.start()


        } catch (ex: Exception) {
            jump()
        }
    }

    private fun jump() {
        if (isFinishing) return
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // User is signed in
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            // User is signed out
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}