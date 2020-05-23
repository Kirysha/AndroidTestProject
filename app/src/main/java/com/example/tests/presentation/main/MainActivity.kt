package com.example.tests.presentation.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.tests.R
import com.example.tests.base.ABaseActivity

class MainActivity  : ABaseActivity() {

    companion object {

        fun show() {
            //TestActivity.show()
        }
    }

    private lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_container)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        showNotification()
        if (savedInstanceState != null)
            return // Не будем пересоздавать фрагмент, пусть берется старый из стека

    }

    fun createChannel(): String {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return ""

        val channelId = "test_channel"
        val channel = NotificationChannel(channelId, "Test channel", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        return channelId
    }

    fun showNotification() {

        //val intent = Intent(this, TestActivity::class.java)
        val pending = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val notification = NotificationCompat.Builder(this, createChannel())
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("title")
            .setContentText("text")
            .setSubText("sub text")
//            .setAutoCancel(true)
//            .setOngoing(true)
            .setContentIntent(pending)
            .build()

        notificationManager.notify(0, notification)
    }
}