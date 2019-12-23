package com.example.myapplication

import android.R
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MIN
import kotlinx.coroutines.runBlocking


class SillyService : Service() {

    override fun onBind(intent: Intent?): IBinder? = Binder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //startForeground()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        startForeground()
    }

    private fun startForeground() {
        val channelId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createNotificationChannel(
                "my_silly_service",
                "My Background SillyService"
            )
        else
            ""

        val intent = Intent(this, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
        val notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.mipmap.sym_def_app_icon)
            .setPriority(PRIORITY_MIN)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(101, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_NONE
        )
            .apply {
                lightColor = Color.RED
                lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            }
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}