package com.example.myapplication.services

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.myapplication.receivers.StopwatchActionReceiver
import kotlinx.coroutines.*

class StopwatchService : Service() {

    private var seconds = 0
    private var isPaused = false
    private var job: Job? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()

        when (intent?.action) {
            "START" -> startStopwatch()
            "PAUSE" -> isPaused = true
            "RESUME" -> isPaused = false
            "STOP" -> stopSelf()
        }

        return START_STICKY
    }

    private fun startStopwatch() {
        startForeground(1, buildNotification("Stopwatch started"))

        job = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(1000)
                if (isPaused) continue
                seconds++
                updateNotification("Running: $seconds seconds")
            }
        }
    }

    private fun buildNotification(text: String): Notification {
        val pauseIntent = createPendingIntent("PAUSE")
        val resumeIntent = createPendingIntent("RESUME")
        val stopIntent = createPendingIntent("STOP")

        return NotificationCompat.Builder(this, "stopwatch_channel")
            .setContentTitle("Stopwatch")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_popup_sync)
            .setOngoing(true)
            .addAction(0, "Pause", pauseIntent)
            .addAction(0, "Resume", resumeIntent)
            .addAction(0, "Stop", stopIntent)
            .build()
    }

    private fun updateNotification(text: String) {
        val notification = buildNotification(text)
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notification)
    }

    private fun createPendingIntent(action: String): PendingIntent {
        val intent = Intent(this, StopwatchActionReceiver::class.java).apply {
            this.action = action
        }
        return PendingIntent.getBroadcast(
            this,
            action.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "stopwatch_channel",
                "Stopwatch Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null
}