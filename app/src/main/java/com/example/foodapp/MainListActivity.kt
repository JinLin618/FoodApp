package com.example.foodapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //open the activity_main first

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, IntroFragment()) //replace the fragment with IntroFragment()
                .commit()
        }
    }
}