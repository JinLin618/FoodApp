package com.example.foodapp



import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView
    private lateinit var favouritesButton: LinearLayout
    private lateinit var genreFilterCard: CardView
    private lateinit var locationFilterCard: CardView
    private lateinit var priceFilterCard: CardView
    private lateinit var ratingFilterCard: CardView
    private lateinit var restaurantRecyclerView: RecyclerView

    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var viewModel: RestaurantViewModel

    // Filter options
    private val genres = listOf("All", "Dessert", "Local Food", "Western", "Chinese", "Fast Food")
    private val locations = listOf("All", "Marina", "CBD", "North", "Lutong", "Tudan")
    private val priceRanges = listOf("All", "$", "$$", "$$$", "$$$$")
    private val ratings = listOf("All", "4+ Stars", "3+ Stars", "2+ Stars")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        initializeViews()
        setupViewModel()
        setupRecyclerView()
        setupSearchView()
        setupFilterButtons()
        observeViewModel()
    }

    private fun initializeViews() {
        searchView = findViewById(R.id.searchView)
        favouritesButton = findViewById(R.id.favouritesButton)
        genreFilterCard = findViewById(R.id.genreFilterCard)
        locationFilterCard = findViewById(R.id.locationFilterCard)
        priceFilterCard = findViewById(R.id.priceFilterCard)
        ratingFilterCard = findViewById(R.id.ratingFilterCard)
        restaurantRecyclerView = findViewById(R.id.restaurantRecyclerView)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[RestaurantViewModel::class.java]
    }

    private fun setupRecyclerView() {
        restaurantAdapter = RestaurantAdapter(
            restaurants = emptyList(),
            onItemClick = { restaurant ->
                // Navigate to restaurant details
                val intent = Intent(this, RestaurantDetailActivity::class.java)
                intent.putExtra("restaurant", restaurant)
                startActivity(intent)
            },
            onFavoriteClick = { restaurant, _ ->
                viewModel.toggleFavorite(restaurant.title)
            }
        )

        restaurantRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = restaurantAdapter
        }
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateSearchQuery(newText ?: "")
                return true
            }
        })
    }

    private fun setupFilterButtons() {
        // Favourites button
        favouritesButton.setOnClickListener {
            viewModel.showFavoritesOnly()
        }

        // Genre filter
        genreFilterCard.setOnClickListener {
            showFilterDialog("Genre", genres) { selectedGenre ->
                viewModel.updateSelectedCategory(selectedGenre)
                findViewById<TextView>(R.id.genreFilterText).text = selectedGenre
            }
        }

        // Location filter
        locationFilterCard.setOnClickListener {
            showFilterDialog("Location", locations) { selectedLocation ->
                viewModel.updateLocationFilter(selectedLocation)
                findViewById<TextView>(R.id.locationFilterText).text = selectedLocation
            }
        }

        // Price filter
        priceFilterCard.setOnClickListener {
            showFilterDialog("Price Range", priceRanges) { selectedPrice ->
                viewModel.updatePriceFilter(selectedPrice)
                findViewById<TextView>(R.id.priceFilterText).text = selectedPrice
            }
        }

        // Rating filter
        ratingFilterCard.setOnClickListener {
            showFilterDialog("Rating", ratings) { selectedRating ->
                viewModel.updateRatingFilter(selectedRating)
                findViewById<TextView>(R.id.ratingFilterText).text = selectedRating
            }
        }
    }

    private fun showFilterDialog(title: String, options: List<String>, onSelect: (String) -> Unit) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Select $title")

        val items = options.toTypedArray()
        builder.setItems(items) { dialog, which ->
            onSelect(items[which])
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun observeViewModel() {
        // Watch for changes in filtered restaurants
        viewModel.filteredRestaurants.observe(this) { restaurants ->
            restaurantAdapter.updateRestaurants(restaurants)
        }

        // Watch for changes in favorites
        viewModel.favoriteRestaurants.observe(this) { favorites ->
            restaurantAdapter.setFavoriteRestaurants(favorites)
        }

        // Watch for changes in selected category
        viewModel.selectedCategory.observe(this) { category ->
            findViewById<TextView>(R.id.genreFilterText).text = if (category == "All") "Genre" else category
        }
    }
}