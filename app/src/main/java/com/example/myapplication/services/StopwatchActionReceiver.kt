package com.example.myapplication.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.myapplication.services.StopwatchService

class StopwatchActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val serviceIntent = Intent(context, StopwatchService::class.java).apply {
            action = intent?.action
        }
        ContextCompat.startForegroundService(context!!, serviceIntent)
    }
}
