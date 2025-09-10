package com.example.foodapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/* This class use to handle the data which will be display in the recycler view */
class RestaurantAdapter(
    private var restaurants: List<Restaurant>, //store the list of restaurants
    private val onItemClick: (Restaurant) -> Unit, //
    private val onFavoriteClick: (Restaurant, Int) -> Unit
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private var favoriteRestaurants = mutableSetOf<String>() //store list of favourite restaurant

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantImage: ImageView = itemView.findViewById(R.id.restaurantImage) //store the restaurant images
        val restaurantTitle: TextView = itemView.findViewById(R.id.restaurantTitle) //store the restaurant name
        val restaurantCategory: TextView = itemView.findViewById(R.id.restaurantCategory) //store the restaurant category
        val restaurantPrice: TextView = itemView.findViewById(R.id.restaurantPrice) //store the restaurant price range
        val ratingText: TextView = itemView.findViewById(R.id.ratingText) //store the restaurant rating
        val bookmarkIcon: ImageView = itemView.findViewById(R.id.bookmarkIcon) //store the favourite icon
    }

    //open the item_restaurant layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]

        // Set restaurant data
        holder.restaurantTitle.text = restaurant.title

        // Set restaurant category
        holder.restaurantCategory.text = restaurant.categories.joinToString(", ")

        // Set price range
        holder.restaurantPrice.text = restaurant.price

        // Set rating
        holder.ratingText.text = restaurant.rating.toString()

        // Set restaurant image (use first image from gallery)
        if (restaurant.imageGallery.isNotEmpty()) {
            holder.restaurantImage.setImageResource(restaurant.imageGallery[0])
        }

        // Handle bookmark state
        val isFavourite = favoriteRestaurants.contains(restaurant.title)
        holder.bookmarkIcon.setImageResource(
            if (isFavourite) R.drawable.heart //when the restaurant marked as favourite, fill in the heart icon
            else R.drawable.empty_favourite //otherwise, it will remain as an empty heart icon
        )

        // when click on itemView, pass "this" restaurant
        holder.itemView.setOnClickListener {
            onItemClick(restaurant)
        }

        // when clicked on the specific restaurant's favourite icon, it will handle favourite state of the restaurant
        holder.bookmarkIcon.setOnClickListener {
            onFavoriteClick(restaurant, position)
        }
    }

    override fun getItemCount() = restaurants.size //to get the total number of restaurants

    // Method to update the restaurant list (for search/filter)
    fun updateRestaurants(newRestaurants: List<Restaurant>) {
        restaurants = newRestaurants
        notifyDataSetChanged()
    }

    // Method to toggle favorite status, add/remove restaurant from the favourite list
    fun toggleFavorite(restaurantTitle: String, position: Int) {
        if (favoriteRestaurants.contains(restaurantTitle)) {
            favoriteRestaurants.remove(restaurantTitle)
        } else {
            favoriteRestaurants.add(restaurantTitle)
        }
        notifyItemChanged(position)
    }

    // Method to get all the favorite restaurants
    fun getFavoriteRestaurants(): Set<String> {
        return favoriteRestaurants.toSet()
    }

    // Method to set favorite restaurants (for state persistence)
    fun setFavoriteRestaurants(favorites: Set<String>) {
        favoriteRestaurants = favorites.toMutableSet()
        notifyDataSetChanged()
    }
}