package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("oxoxo", "enter")
        CoroutineScope(IO).launch {
            Log.e("oxoxo", "launch")
            for (i in 1..100) {
                startStop(i)
            }
        }
    }

    private suspend fun startStop(i: Int) {
        withContext(Main) {
            Log.e("oxoxo", "1 step = $i")
            ContextCompat.startForegroundService(
                baseContext,
                Intent(applicationContext, SillyService::class.java)
            )
            delay(2L)
            applicationContext.stopService(Intent(applicationContext, SillyService::class.java))
        }
    }
}
