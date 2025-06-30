package com.example.myapplication.ui.screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.myapplication.services.StopwatchService

@Composable
fun StopwatchScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            val intent = Intent(context, StopwatchService::class.java).apply {
                action = "START"
            }
            ContextCompat.startForegroundService(context, intent)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Start Stopwatch")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val intent = Intent(context, StopwatchService::class.java).apply {
                action = "PAUSE"
            }
            ContextCompat.startForegroundService(context, intent)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Pause Stopwatch")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val intent = Intent(context, StopwatchService::class.java).apply {
                action = "RESUME"
            }
            ContextCompat.startForegroundService(context, intent)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Resume Stopwatch")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val intent = Intent(context, StopwatchService::class.java).apply {
                action = "STOP"
            }
            ContextCompat.startForegroundService(context, intent)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Stop Stopwatch")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Implicit intent to open YouTube video
        Button(onClick = {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
            }
            context.startActivity(intent)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Open YouTube Video")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Send custom intent (for testing onNewIntent)
        Button(onClick = {
            val intent = Intent("com.example.myapplication.CUSTOM_ACTION")
            context.startActivity(intent)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Send Custom Intent")
        }
    }
}
