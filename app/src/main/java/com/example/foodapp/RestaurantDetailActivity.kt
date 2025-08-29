package com.example.foodapp


import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RestaurantDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a simple layout programmatically
        val textView = TextView(this).apply {
            text = "Restaurant Detail Screen"
            textSize = 24f
            setPadding(32, 32, 32, 32)
        }

        setContentView(textView)

        // Get the restaurant data

        // Get the restaurant data
        val restaurant = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("restaurant", Restaurant::class.java)
        } else {
            @Suppress("DEPRECATION") // Suppress deprecation warning for older versions
            intent.getParcelableExtra<Restaurant>("restaurant")
        }

        if (restaurant != null) {
            textView.text = "Restaurant: ${restaurant.title}\n\nDescription: ${restaurant.description}"
        }
    }
}