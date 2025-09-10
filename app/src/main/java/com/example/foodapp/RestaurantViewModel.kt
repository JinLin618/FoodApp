package com.example.foodapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

// This ViewModel owns *all* screen state (search text, filters, favorites).
// Because the Fragment obtains it with ViewModelProvider(requireActivity()),
// this state survives configuration changes and is shared by fragments in the same Activity.
class RestaurantViewModel : ViewModel() {

    // This is the full immutable source list that we never mutate; it acts like a repository cache.
    private val allRestaurants: List<Restaurant> = RestaurantData.getAllData()

    // This holds the *current* list after applying search, filters, and "favorites only".
    private val _filteredRestaurants = MutableLiveData<List<Restaurant>>(allRestaurants)
    val filteredRestaurants: LiveData<List<Restaurant>> = _filteredRestaurants

    // These LiveData hold the current values for search and each filter.
    // We initialize them to sensible defaults so the UI has a known state on first render.
    private val _searchQuery = MutableLiveData("")
    val searchQuery: LiveData<String> = _searchQuery

    private val _selectedCategory = MutableLiveData("All")
    val selectedCategory: LiveData<String> = _selectedCategory

    private val _selectedLocation = MutableLiveData("All")
    val selectedLocation: LiveData<String> = _selectedLocation

    // We use "All" (no sorting) | "Highest" | "Lowest" for both price and rating.
    private val _priceFilter = MutableLiveData("All")
    val priceFilter: LiveData<String> = _priceFilter

    private val _ratingFilter = MutableLiveData("All")
    val ratingFilter: LiveData<String> = _ratingFilter

    // Favorites are identified by a unique key; in this sample we use title.
    // In production you should prefer a unique ID field to avoid collisions.
    private val _favoriteRestaurants = MutableLiveData<Set<String>>(emptySet())
    val favoriteRestaurants: LiveData<Set<String>> = _favoriteRestaurants

    // This flag switches the list to only show items whose title is in the favorites set.
    private val _showFavoritesOnly = MutableLiveData(false)
    val showFavoritesOnly: LiveData<Boolean> = _showFavoritesOnly

    // ----- Public updates (called by the Fragment UI) -----

    // When the search text changes, we update the LiveData and recompute the filtered list.
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        filterRestaurants()
    }

    // When the category changes, we store it and recompute.
    fun updateSelectedCategory(category: String) {
        _selectedCategory.value = category
        filterRestaurants()
    }

    // When the location changes, we store it and recompute.
    fun updateLocationFilter(location: String) {
        _selectedLocation.value = location
        filterRestaurants()
    }

    // When the price sort changes, we store "All"/"Highest"/"Lowest" and recompute.
    fun updatePriceFilter(price: String) {
        _priceFilter.value = price
        filterRestaurants()
    }

    // When the rating sort changes, we store "All"/"Highest"/"Lowest" and recompute.
    fun updateRatingFilter(rating: String) {
        _ratingFilter.value = rating
        filterRestaurants()
    }

    // This toggles "favorites only" mode; we flip the boolean and recompute.
    fun showFavoritesOnly() {
        _showFavoritesOnly.value = !(_showFavoritesOnly.value ?: false)
        filterRestaurants()
    }

    // This toggles the favorite state for a restaurant title and recomputes the list.
    fun toggleFavorite(restaurantTitle: String) {
        val current = _favoriteRestaurants.value?.toMutableSet() ?: mutableSetOf()
        if (current.contains(restaurantTitle)) current.remove(restaurantTitle)
        else current.add(restaurantTitle)
        _favoriteRestaurants.value = current
        filterRestaurants()
    }

    // ----- Core filtering/sorting pipeline (pure function of current state) -----

    // This reads the latest state (search text, filters, favorites) and produces the final list.
    private fun filterRestaurants() {
        val query = _searchQuery.value.orEmpty()
        val category = _selectedCategory.value ?: "All"
        val location = _selectedLocation.value ?: "All"
        val price = _priceFilter.value ?: "All"
        val rating = _ratingFilter.value ?: "All"
        val favorites = _favoriteRestaurants.value ?: emptySet()
        val favoritesOnly = _showFavoritesOnly.value ?: false

        // Step 1: filter by category, search text (title/description), location (address), and favorites-only flag.
        val filtered = allRestaurants.filter { restaurant ->
            // Category matches if "All" or any category contains the selected text (case-insensitive).
            val matchesCategory = category == "All" ||
                    restaurant.categories.any { it.contains(category, ignoreCase = true) }

            // Search matches if empty or found in title or description (case-insensitive).
            val matchesSearch = query.isEmpty() ||
                    restaurant.title.contains(query, ignoreCase = true) ||
                    restaurant.description.contains(query, ignoreCase = true)

            // Location matches if "All" or the address contains the selected area/keyword.
            val matchesLocation = location == "All" ||
                    restaurant.address.contains(location, ignoreCase = true)

            // Favorites matches if we are NOT in favorites-only mode, or the title is in the favorites set.
            val matchesFavorites = !favoritesOnly || favorites.contains(restaurant.title)

            matchesCategory && matchesSearch && matchesLocation && matchesFavorites
        }

        // Step 2: apply price sorting if requested (we assume 'priceLevel' is a 1..4 scale).
        val priceSorted = when (price) {
            "Lowest"  -> filtered.sortedBy { it.priceLevel }
            "Highest" -> filtered.sortedByDescending { it.priceLevel }
            else      -> filtered // "All" means keep current order
        }

        // Step 3: apply rating sorting if requested.
        val fullySorted = when (rating) {
            "Lowest"  -> priceSorted.sortedBy { it.rating }
            "Highest" -> priceSorted.sortedByDescending { it.rating }
            else      -> priceSorted // "All" keeps the list as-is
        }

        // Step 4: publish the final list; RecyclerView will refresh via the Fragment observer.
        _filteredRestaurants.value = fullySorted
    }
}
