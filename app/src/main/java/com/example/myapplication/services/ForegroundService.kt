package com.example.myapplication.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.Build
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat



class ForegroundService : Service() {

    companion object {
        const val CHANNEL_ID = "ForegroundServiceChannel"
    }

    override fun onCreate() {
        super.onCreate()


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Foreground Service"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        val notification = createNotification()
        startForeground(1, notification)
        return START_STICKY
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Car Rental App")
            .setContentText("Foreground service is running...")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
