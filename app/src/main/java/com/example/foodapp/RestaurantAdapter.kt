package com.example.foodapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter(
    private var restaurants: List<Restaurant>,
    private val onItemClick: (Restaurant) -> Unit,
    private val onFavoriteClick: (Restaurant, Int) -> Unit
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private var favoriteRestaurants = mutableSetOf<String>()

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantImage: ImageView = itemView.findViewById(R.id.restaurantImage)
        val restaurantTitle: TextView = itemView.findViewById(R.id.restaurantTitle)
        val restaurantCategory: TextView = itemView.findViewById(R.id.restaurantCategory)
        val restaurantPrice: TextView = itemView.findViewById(R.id.restaurantPrice)
        val ratingText: TextView = itemView.findViewById(R.id.ratingText)
        val bookmarkIcon: ImageView = itemView.findViewById(R.id.bookmarkIcon)
        val star1: ImageView = itemView.findViewById(R.id.star1)
        val star2: ImageView = itemView.findViewById(R.id.star2)
        val star3: ImageView = itemView.findViewById(R.id.star3)
        val star4: ImageView = itemView.findViewById(R.id.star4)
        val star5: ImageView = itemView.findViewById(R.id.star5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]

        // Set restaurant data
        holder.restaurantTitle.text = restaurant.title
        holder.restaurantCategory.text = restaurant.categories.firstOrNull() ?: "Restaurant"

        // Set price level
        val priceSymbol = when (restaurant.priceLevel) {
            1 -> "$"
            2 -> "$$"
            3 -> "$$$"
            4 -> "$$$$"
            else -> "$$"
        }
        holder.restaurantPrice.text = priceSymbol

        // To this:
        holder.restaurantPrice.text = restaurant.price

        // Set rating
        holder.ratingText.text = restaurant.rating.toString()
        setStarRating(holder, restaurant.rating)

        // Set restaurant image (use first image from gallery)
        if (restaurant.imageGallery.isNotEmpty()) {
            holder.restaurantImage.setImageResource(restaurant.imageGallery[0])
        }

        // Handle bookmark state
        val isBookmarked = favoriteRestaurants.contains(restaurant.title)
        holder.bookmarkIcon.setImageResource(
            if (isBookmarked) R.drawable.heart
            else R.drawable.empty_favourite
        )

        // Set click listeners
        holder.itemView.setOnClickListener {
            onItemClick(restaurant)
        }

        holder.bookmarkIcon.setOnClickListener {
            onFavoriteClick(restaurant, position)
        }
    }

    private fun setStarRating(holder: RestaurantViewHolder, rating: Float) {
        val stars = listOf(holder.star1, holder.star2, holder.star3, holder.star4, holder.star5)
        val fullStars = rating.toInt()

        stars.forEachIndexed { index, star ->
            when {
                index < fullStars -> star.setImageResource(R.drawable.coffee)
                index == fullStars && rating % 1 >= 0.5 -> star.setImageResource(R.drawable.cake1) // For half stars, use filled for now
                else -> star.setImageResource(R.drawable.steak)
            }
        }
    }

    override fun getItemCount() = restaurants.size

    // Method to update the restaurant list (for search/filter)
    fun updateRestaurants(newRestaurants: List<Restaurant>) {
        restaurants = newRestaurants
        notifyDataSetChanged()
    }

    // Method to toggle favorite status
    fun toggleFavorite(restaurantTitle: String, position: Int) {
        if (favoriteRestaurants.contains(restaurantTitle)) {
            favoriteRestaurants.remove(restaurantTitle)
        } else {
            favoriteRestaurants.add(restaurantTitle)
        }
        notifyItemChanged(position)
    }

    // Method to get favorite restaurants
    fun getFavoriteRestaurants(): Set<String> {
        return favoriteRestaurants.toSet()
    }

    // Method to set favorite restaurants (for state persistence)
    fun setFavoriteRestaurants(favorites: Set<String>) {
        favoriteRestaurants = favorites.toMutableSet()
        notifyDataSetChanged()
    }
}