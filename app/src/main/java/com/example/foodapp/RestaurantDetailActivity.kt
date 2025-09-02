package com.example.foodapp


import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class RestaurantDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_desc_page)

        val titleView: TextView = findViewById(R.id.restaurantName)
        val descView: TextView = findViewById(R.id.restaurantDescription)
        val ratingBar: RatingBar = findViewById(R.id.restaurantRating)
        val ratingView: TextView = findViewById(R.id.ratingValue)
        val reviewCountView : TextView = findViewById(R.id.reviewCount)
        val locationView : TextView = findViewById(R.id.restaurantLocation)
        val contactView : TextView = findViewById(R.id.restaurantContact)
        val hoursView: TextView = findViewById(R.id.restaurantOpenHours)
        val favBtn: ImageButton = findViewById(R.id.favouritesBtn)

        findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Get the restaurant data
        val restaurant = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("restaurant", Restaurant::class.java)
        } else {
            @Suppress("DEPRECATION") // Suppress deprecation warning for older versions
            intent.getParcelableExtra<Restaurant>("restaurant")
        }

        if (restaurant != null) {
            titleView.text = restaurant.title
            descView.text = restaurant.description
            ratingView.text = restaurant.rating.toString()
            reviewCountView.text = "(${restaurant.reviewCount})"
            ratingBar.rating = restaurant.rating
            locationView.text = restaurant.address
            contactView.text = restaurant.phoneNumber
            hoursView.text =
                restaurant.hours.takeIf { it.isNotEmpty() }?.let { formatHours(it) } ?: "—"


            val pager = findViewById<ViewPager2>(R.id.restaurantImagePager)
            val dots = findViewById<TabLayout>(R.id.imageDots)

            pager.adapter = ImagePagerAdapter(restaurant.imageGallery)

            TabLayoutMediator(dots, pager) { tab, _ ->
                tab.setCustomView(R.layout.item_dot)
            }.attach()

            dots.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    tab.customView?.isSelected = true
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    tab.customView?.isSelected = false
                }

                override fun onTabReselected(tab: TabLayout.Tab) {}
            })

            dots.getTabAt(pager.currentItem)?.customView?.isSelected = true

            // Favourite button state and interaction
            val isFav = RestaurantStateStore.favoriteRestaurants.value?.contains(restaurant.title) == true
            favBtn.setImageResource(if (isFav) R.drawable.heart else R.drawable.empty_favourite)
            favBtn.setOnClickListener {
                RestaurantStateStore.toggleFavorite(restaurant.title)
                val newIsFav = RestaurantStateStore.favoriteRestaurants.value?.contains(restaurant.title) == true
                favBtn.setImageResource(if (newIsFav) R.drawable.heart else R.drawable.empty_favourite)
            }
        }
    }
    private fun formatHours(hours: Map<String, String>): String {
        // Example map: {"Mon-Fri" to "10:00–22:00", "Sat" to "10:00–23:00", "Sun" to "Closed"}
        // Result:
        // Mon-Fri: 10:00–22:00
        // Sat: 10:00–23:00
        // Sun: Closed
        return hours.entries.joinToString("\n") { (day, time) -> "$day: $time" }
    }
}