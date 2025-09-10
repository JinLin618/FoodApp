package com.example.foodapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class  RestaurantDetailFragment : Fragment(R.layout.activity_restaurant_desc_page) {
    companion object {
        private const val ARG_RESTAURANT = "restaurant"
        fun newInstance(restaurant: Restaurant) = RestaurantDetailFragment().apply {
            arguments = bundleOf(ARG_RESTAURANT to restaurant) // pass the parcelable
        }
    }

    private lateinit var viewModel: RestaurantViewModel
    private lateinit var restaurant: Restaurant

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1) shared Activity-scoped VM → same instance used by MainListFragment
        viewModel = ViewModelProvider(requireActivity())[RestaurantViewModel::class.java]

        // 2) get Restaurant argument that was passed during navigation
        restaurant = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(ARG_RESTAURANT, Restaurant::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            requireArguments().getParcelable<Restaurant>(ARG_RESTAURANT)!!
        }

        // 3) find views from the reused layout
        val titleView: TextView = view.findViewById(R.id.restaurantName)
        val descView: TextView = view.findViewById(R.id.restaurantDescription)
        val ratingBar: RatingBar = view.findViewById(R.id.restaurantRating)
        val ratingView: TextView = view.findViewById(R.id.ratingValue)
        val reviewCountView: TextView = view.findViewById(R.id.reviewCount)
        val priceView: TextView = view.findViewById(R.id.restaurantPrice)
        val priceLevelView: TextView = view.findViewById(R.id.priceLevel)
        val locationView: TextView = view.findViewById(R.id.restaurantLocation)
        val contactView: TextView = view.findViewById(R.id.restaurantContact)
        val hoursView: TextView = view.findViewById(R.id.restaurantOpenHours)
        val favBtn: ImageButton = view.findViewById(R.id.favouritesBtn)
        val mapLink: TextView = view.findViewById(R.id.restaurantMapLink)
        val backBtn: ImageButton = view.findViewById(R.id.backBtn)

        backBtn.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }

        // 4) bind restaurant fields
        titleView.text = restaurant.title
        descView.text = restaurant.description
        ratingView.text = restaurant.rating.toString()
        reviewCountView.text = "(${restaurant.reviewCount})"
        ratingBar.rating = restaurant.rating
        locationView.text = restaurant.address
        contactView.text = restaurant.phoneNumber
        priceView.text = restaurant.price
        priceLevelView.text = "(${when (restaurant.priceLevel) {
            1 -> "$"; 2 -> "$$"; 3 -> "$$$"; 4 -> "$$$$"; else -> "$$"
        }})"

        // maps link
        mapLink.text = restaurant.mapsUrl
        mapLink.paintFlags = mapLink.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        mapLink.setOnClickListener {
            val url = restaurant.mapsUrl
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                setPackage("com.google.android.apps.maps")
            }
            try { startActivity(intent) }
            catch(_: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }

        // hours block
        hoursView.text = restaurant.hours.takeIf { it.isNotEmpty() }
            ?.entries?.joinToString("\n") { (day, time) -> "$day: $time" } ?: "—"

        // image pager + dots
        val pager = view.findViewById<ViewPager2>(R.id.restaurantImagePager)
        val dots = view.findViewById<TabLayout>(R.id.imageDots)
        pager.adapter = ImagePagerAdapter(restaurant.imageGallery)
        TabLayoutMediator(dots, pager) { tab, _ -> tab.setCustomView(R.layout.item_dot) }.attach()
        dots.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) { tab.customView?.isSelected = true }
            override fun onTabUnselected(tab: TabLayout.Tab) { tab.customView?.isSelected = false }
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        dots.getTabAt(pager.currentItem)?.customView?.isSelected = true

        // 5) FAVOURITES: observe shared VM → icon updates instantly across both screens
        viewModel.favoriteRestaurants.observe(viewLifecycleOwner) { favs ->
            val isFav = favs.contains(restaurant.title)
            favBtn.setImageResource(if (isFav) R.drawable.ic_favourite_filled else R.drawable.ic_favorite)
        }
        favBtn.setOnClickListener {
            viewModel.toggleFavorite(restaurant.title) // updates shared state → list screen updates too
        }
    }
}
