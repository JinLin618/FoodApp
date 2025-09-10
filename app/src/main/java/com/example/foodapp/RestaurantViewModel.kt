package com.example.foodapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

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
    private val _selectedLocation = MutableLiveData<String>()
    val selectedLocation: LiveData<String> = _selectedLocation

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

    private val _selectedRestaurant = MutableLiveData<Restaurant>()
    val selectedRestaurant: LiveData<Restaurant> = _selectRestaurant


    private val stateFavoriteObserver = Observer<Set<String>> { favorites: Set<String> ->
        _favoriteRestaurants.value = favorites
        filterRestaurants()
    }

    init {
        _searchQuery.value = RestaurantStateStore.searchQuery.value ?: ""
        _selectedCategory.value = RestaurantStateStore.selectedCategory.value ?: "All"
        _selectedLocation.value = RestaurantStateStore.selectedLocation.value ?: "All"
        _priceFilter.value = RestaurantStateStore.priceFilter.value ?: "-"
        _ratingFilter.value = RestaurantStateStore.ratingFilter.value ?: "-"
        _favoriteRestaurants.value = RestaurantStateStore.favoriteRestaurants.value ?: emptySet()
        _showFavoritesOnly.value = RestaurantStateStore.showFavoritesOnly.value ?: false
        _filteredRestaurants.value = allRestaurants

        RestaurantStateStore.favoriteRestaurants.observeForever(stateFavoriteObserver)
    }

    fun selectRestaurant(restaurant: Restaurant){
        _selectedRestaurant.value = restaurant
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        RestaurantStateStore.setSearchQuery(query)
        filterRestaurants()
    }

    fun updateSelectedCategory(category: String) {
        _selectedCategory.value = category
        RestaurantStateStore.setSelectedCategory(category)
        filterRestaurants()
    }

    fun updateLocationFilter(location: String) {
        _selectedLocation.value = location
        RestaurantStateStore.setLocationFilter(location)
        filterRestaurants()
    }

    fun updatePriceFilter(price: String) {
        _priceFilter.value = price
        RestaurantStateStore.setPriceFilter(price)
        filterRestaurants()
    }

    fun updateRatingFilter(rating: String) {
        _ratingFilter.value = rating
        RestaurantStateStore.setRatingFilter(rating)
        filterRestaurants()
    }

    fun showFavoritesOnly() {
        val currentValue = _showFavoritesOnly.value ?: false
        _showFavoritesOnly.value = !currentValue
        RestaurantStateStore.toggleFavoritesOnly()
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
        RestaurantStateStore.toggleFavorite(restaurantTitle)
        filterRestaurants()
    }

    private fun filterRestaurants() {
        val query = _searchQuery.value ?: ""
        val category = _selectedCategory.value ?: "All"
        val location = _selectedLocation.value ?: "All"
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

            // Favorites filter
            val matchesFavorites = !favoritesOnly || favorites.contains(restaurant.title)

            matchesCategory &&
                    matchesSearch &&
                    matchesLocation &&
                    matchesFavorites
        }

        var finalList = filtered

        //  Price sorting
        finalList = when (price) {
            "Lowest" -> finalList.sortedBy { it.priceLevel }   // assuming you have priceLevel
            "Highest" -> finalList.sortedByDescending { it.priceLevel }
            else -> finalList // "All" → no sorting applied
        }

        //  Rating sorting
        finalList = when (rating) {
            "Lowest" -> finalList.sortedBy { it.rating }
            "Highest" -> finalList.sortedByDescending { it.rating }
            else -> finalList // "All" → no sorting applied
        }

        _filteredRestaurants.value = finalList
    }

    override fun onCleared() {
        super.onCleared()
        RestaurantStateStore.favoriteRestaurants.removeObserver(stateFavoriteObserver)
    }
}