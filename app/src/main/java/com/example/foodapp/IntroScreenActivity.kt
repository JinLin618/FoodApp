package com.example.foodapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class IntroScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro_screen)

        val btnStart = findViewById<Button>(R.id.startButton)

        btnStart.setOnClickListener{
            val intent = Intent(this, MainListActivity::class.java)
            startActivity(intent)
            setContentView(R.layout.activity_main_screen)
        }
    }
}