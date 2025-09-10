package com.example.foodapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class RestaurantDetailFragment : Fragment(R.layout.activity_restaurant_desc_page){

    private val viewModel: RestaurantViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val titleView: TextView = view.findViewById(R.id.restaurantName)
        val descView: TextView = view.findViewById(R.id.restaurantDescription)
        val ratingBar: RatingBar = view.findViewById(R.id.restaurantRating)
        val ratingView: TextView = view.findViewById(R.id.ratingValue)
        val reviewCountView : TextView = view.findViewById(R.id.reviewCount)
        val priceView : TextView = view.findViewById(R.id.restaurantPrice)
        val priceLevelView : TextView = view.findViewById(R.id.priceLevel)
        val locationView : TextView = view.findViewById(R.id.restaurantLocation)
        val contactView : TextView = view.findViewById(R.id.restaurantContact)
        val hoursView: TextView = view.findViewById(R.id.restaurantOpenHours)
        val favBtn: ImageButton = view.findViewById(R.id.favouritesBtn)
        val mapLink: TextView = view.findViewById(R.id.restaurantMapLink)



        view.findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // Get the restaurant data
        /*val restaurant = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("restaurant", Restaurant::class.java)
        } else {
            @Suppress("DEPRECATION") // Suppress deprecation warning for older versions
            intent.getParcelableExtra<Restaurant>("restaurant")
        }*/

        viewModel.selected

        if (restaurant != null) {
            titleView.text = restaurant.title
            descView.text = restaurant.description
            ratingView.text = restaurant.rating.toString()
            reviewCountView.text = "(${restaurant.reviewCount})"
            ratingBar.rating = restaurant.rating
            locationView.text = restaurant.address
            contactView.text = restaurant.phoneNumber
            priceView.text = restaurant.price
            priceLevelView.text = "(${when (restaurant.priceLevel) {
                1 -> "$"
                2 -> "$$"
                3 -> "$$$"
                4 -> "$$$$"
                else -> "$$"
            }})"

            mapLink.text = restaurant.mapsUrl
            mapLink.setOnClickListener{
                val url = restaurant.mapsUrl
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply{
                    setPackage("com.google.android.apps.maps")
                }
                try{
                    startActivity(intent)
                }catch(e: ActivityNotFoundException){

                    val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(fallbackIntent)
                }
            }

            //underline the url link
            mapLink.paintFlags = mapLink.paintFlags or Paint.UNDERLINE_TEXT_FLAG

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
            favBtn.setImageResource(if (isFav) R.drawable.ic_favourite_filled else R.drawable.ic_favorite)
            favBtn.setOnClickListener {
                RestaurantStateStore.toggleFavorite(restaurant.title)
                val newIsFav = RestaurantStateStore.favoriteRestaurants.value?.contains(restaurant.title) == true
                favBtn.setImageResource(if (newIsFav) R.drawable.ic_favourite_filled else R.drawable.ic_favorite)
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