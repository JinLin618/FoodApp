package com.example.foodapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class RestaurantViewModel : ViewModel() {

    // Store and create variable (livedata) that will auto update the screen when their value change
    // Store all the restaurants in this variable

    // Store all restaurants
    private val allRestaurants = RestaurantData.getAllData()

    // Store the current filtered list
    private val _filteredRestaurants = MutableLiveData<List<Restaurant>>()
    val filteredRestaurants: LiveData<List<Restaurant>> = _filteredRestaurants

    // Store current search query
    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery

    // Store current selected category
    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String> = _selectedCategory

    // Store current location filter
    private val _locationFilter = MutableLiveData<String>()
    val locationFilter: LiveData<String> = _locationFilter

    // Store current price filter
    private val _priceFilter = MutableLiveData<String>()
    val priceFilter: LiveData<String> = _priceFilter

    // Store current rating filter
    private val _ratingFilter = MutableLiveData<String>()
    val ratingFilter: LiveData<String> = _ratingFilter

    // Store favorite restaurants
    private val _favoriteRestaurants = MutableLiveData<Set<String>>()
    val favoriteRestaurants: LiveData<Set<String>> = _favoriteRestaurants

    // Store if showing favorites only
    private val _showFavoritesOnly = MutableLiveData<Boolean>()
    val showFavoritesOnly: LiveData<Boolean> = _showFavoritesOnly

    init {
        // Initialize with default values
        _searchQuery.value = ""
        _selectedCategory.value = "All"
        _locationFilter.value = "All"
        _priceFilter.value = "All"
        _ratingFilter.value = "All"
        _favoriteRestaurants.value = emptySet()
        _showFavoritesOnly.value = false
        _filteredRestaurants.value = allRestaurants
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        filterRestaurants()
    }

    fun updateSelectedCategory(category: String) {
        _selectedCategory.value = category
        filterRestaurants()
    }

    fun updateLocationFilter(location: String) {
        _locationFilter.value = location
        filterRestaurants()
    }

    fun updatePriceFilter(price: String) {
        _priceFilter.value = price
        filterRestaurants()
    }

    fun updateRatingFilter(rating: String) {
        _ratingFilter.value = rating
        filterRestaurants()
    }

    fun showFavoritesOnly() {
        val currentValue = _showFavoritesOnly.value ?: false
        _showFavoritesOnly.value = !currentValue
        filterRestaurants()
    }

    fun toggleFavorite(restaurantTitle: String) {
        val currentFavorites = _favoriteRestaurants.value?.toMutableSet() ?: mutableSetOf()
        if (currentFavorites.contains(restaurantTitle)) {
            currentFavorites.remove(restaurantTitle)
        } else {
            currentFavorites.add(restaurantTitle)
        }
        _favoriteRestaurants.value = currentFavorites
        filterRestaurants()
    }

    private fun filterRestaurants() {
        val query = _searchQuery.value ?: ""
        val category = _selectedCategory.value ?: "All"
        val location = _locationFilter.value ?: "All"
        val price = _priceFilter.value ?: "All"
        val rating = _ratingFilter.value ?: "All"
        val favorites = _favoriteRestaurants.value ?: emptySet()
        val favoritesOnly = _showFavoritesOnly.value ?: false

        val filtered = allRestaurants.filter { restaurant ->
            // Category filter
            val matchesCategory = category == "All" ||
                    restaurant.categories.any { it.contains(category, ignoreCase = true) }

            // Search filter
            val matchesSearch = query.isEmpty() ||
                    restaurant.title.contains(query, ignoreCase = true) ||
                    restaurant.description.contains(query, ignoreCase = true)

            // Location filter (check address)
            val matchesLocation = location == "All" ||
                    restaurant.address.contains(location, ignoreCase = true)

            // Price filter


            // Rating filter
            val matchesRating = rating == "All" || when (rating) {
                "4+ Stars" -> restaurant.rating >= 4.0f
                "3+ Stars" -> restaurant.rating >= 3.0f
                "2+ Stars" -> restaurant.rating >= 2.0f
                else -> true
            }

            // Favorites filter
            val matchesFavorites = !favoritesOnly || favorites.contains(restaurant.title)

            //  Final filter condition stored in a variable for clarity
            val shouldInclude = matchesCategory &&
                    matchesSearch &&
                    matchesLocation &&
                    matchesRating &&
                    matchesFavorites

            shouldInclude
        }

        _filteredRestaurants.value = filtered
    }
}