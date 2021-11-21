package dev.kingbond.notify.ui.event

import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dev.kingbond.notify.R
import dev.kingbond.notify.ui.home.HomeActivity

class AlarmReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {

        //music
        var mp: MediaPlayer = MediaPlayer.create(context, R.raw.alarm_tone)
        mp.start()

        val i = Intent(context, HomeActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        /*Handler().postDelayed({

        }, 15000)*/


        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)

        val notificationManager = NotificationManagerCompat.from(context!!)
        val builder = NotificationCompat.Builder(context!!, "notify")
            .setSmallIcon(R.drawable.ic_home_icon)
            .setContentTitle(notificationManager.getNotificationChannel("notify")?.name)
            .setContentText(notificationManager.getNotificationChannel("notify")?.description)
            .setAutoCancel(false)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)


        notificationManager.notify(123, builder.build())
    }

}