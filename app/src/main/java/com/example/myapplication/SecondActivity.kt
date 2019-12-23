package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second)

        findViewById<View>(R.id.textView2).setOnClickListener {
            baseContext.stopService(Intent(applicationContext, SillyService::class.java))
        }
    }
}