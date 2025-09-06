package com.example.foodapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/* state store to share UI state (filters, favorites) across screens
   and survive configuration changes via ViewModel observers. */
   
object RestaurantStateStore {

    private val _searchQuery = MutableLiveData("")
    val searchQuery: LiveData<String> = _searchQuery

    private val _selectedCategory = MutableLiveData("All")
    val selectedCategory: LiveData<String> = _selectedCategory

    private val _selectedLocation = MutableLiveData("All")
    val selectedLocation: LiveData<String> = _selectedLocation

    private val _priceFilter = MutableLiveData("All")
    val priceFilter: LiveData<String> = _priceFilter

    private val _ratingFilter = MutableLiveData("All")
    val ratingFilter: LiveData<String> = _ratingFilter

    private val _favoriteRestaurants = MutableLiveData<Set<String>>(emptySet())
    val favoriteRestaurants: LiveData<Set<String>> = _favoriteRestaurants

    private val _showFavoritesOnly = MutableLiveData(false)
    val showFavoritesOnly: LiveData<Boolean> = _showFavoritesOnly

    fun setSearchQuery(query: String) { _searchQuery.value = query }
    fun setSelectedCategory(category: String) { _selectedCategory.value = category }
    fun setLocationFilter(location: String) { _selectedLocation.value = location }
    fun setPriceFilter(price: String) { _priceFilter.value = price }
    fun setRatingFilter(rating: String) { _ratingFilter.value = rating }

    fun toggleFavoritesOnly() { _showFavoritesOnly.value = !(_showFavoritesOnly.value ?: false) }

    fun toggleFavorite(restaurantTitle: String) {
        val current = _favoriteRestaurants.value?.toMutableSet() ?: mutableSetOf()
        if (current.contains(restaurantTitle)) current.remove(restaurantTitle) else current.add(restaurantTitle)
        _favoriteRestaurants.value = current
    }
}


