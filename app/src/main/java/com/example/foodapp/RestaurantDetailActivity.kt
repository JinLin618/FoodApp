package com.example.foodapp


import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RestaurantDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_desc_page)

        val titleView: TextView = findViewById(R.id.restaurantName)
        val descView: TextView = findViewById(R.id.restaurantDescription)
        val ratingView: TextView = findViewById(R.id.restaurantRating)


        // Get the restaurant data
        val restaurant = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("restaurant", Restaurant::class.java)
        } else {
            @Suppress("DEPRECATION") // Suppress deprecation warning for older versions
            intent.getParcelableExtra<Restaurant>("restaurant")
        }

        if (restaurant != null){
            titleView.text = restaurant.title
            descView.text = restaurant.description
            ratingView.text = restaurant.rating.toString()
        }
    }
}