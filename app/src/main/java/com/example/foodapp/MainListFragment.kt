package com.example.foodapp

import android.content.Intent
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainListFragment : Fragment(R.layout.activity_main_screen) {

    private lateinit var searchView: SearchView
    private lateinit var favouritesButton: LinearLayout
    private lateinit var genreFilterCard: CardView
    private lateinit var locationFilterCard: CardView
    private lateinit var priceFilterCard: CardView
    private lateinit var ratingFilterCard: CardView
    private lateinit var restaurantRecyclerView: RecyclerView

    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var viewModel: RestaurantViewModel

    private val genres = listOf("All", "Dessert", "Local Food", "Western", "Chinese", "Fast Food")
    private val locations = listOf("All", "Marina", "CBD", "North", "Lutong", "Tudan")
    private val priceRanges = listOf("All", "$", "$$", "$$$", "$$$$")
    private val ratings = listOf("All", "4+ Stars", "3+ Stars", "2+ Stars")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = view.findViewById(R.id.searchView)
        favouritesButton = view.findViewById(R.id.favouritesButton)
        genreFilterCard = view.findViewById(R.id.genreFilterCard)
        locationFilterCard = view.findViewById(R.id.locationFilterCard)
        priceFilterCard = view.findViewById(R.id.priceFilterCard)
        ratingFilterCard = view.findViewById(R.id.ratingFilterCard)
        restaurantRecyclerView = view.findViewById(R.id.restaurantRecyclerView)

        viewModel = ViewModelProvider(this)[RestaurantViewModel::class.java]

        restaurantAdapter = RestaurantAdapter(
            restaurants = emptyList(),
            onItemClick = { restaurant: Restaurant ->
                val intent = Intent(requireContext(), RestaurantDetailActivity::class.java)
                intent.putExtra("restaurant", restaurant)
                startActivity(intent)
            },
            onFavoriteClick = { restaurant: Restaurant, _: Int ->
                viewModel.toggleFavorite(restaurant.title)
            }
        )

        restaurantRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = restaurantAdapter
        }

        setupSearchView()
        setupFilterButtons(view)
        observeViewModel(view)
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

    private fun setupFilterButtons(root: View) {
        favouritesButton.setOnClickListener {
            viewModel.showFavoritesOnly()
        }

        genreFilterCard.setOnClickListener {
            showFilterDialog("Genre", genres) { selectedGenre ->
                viewModel.updateSelectedCategory(selectedGenre)
                root.findViewById<TextView>(R.id.genreFilterText).text = selectedGenre
            }
        }

        locationFilterCard.setOnClickListener {
            showFilterDialog("Location", locations) { selectedLocation ->
                viewModel.updateLocationFilter(selectedLocation)
                root.findViewById<TextView>(R.id.locationFilterText).text = selectedLocation
            }
        }

        priceFilterCard.setOnClickListener {
            showFilterDialog("Price Range", priceRanges) { selectedPrice ->
                viewModel.updatePriceFilter(selectedPrice)
                root.findViewById<TextView>(R.id.priceFilterText).text = selectedPrice
            }
        }

        ratingFilterCard.setOnClickListener {
            showFilterDialog("Rating", ratings) { selectedRating ->
                viewModel.updateRatingFilter(selectedRating)
                root.findViewById<TextView>(R.id.ratingFilterText).text = selectedRating
            }
        }
    }

    private fun showFilterDialog(title: String, options: List<String>, onSelect: (String) -> Unit) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select $title")

        val items = options.toTypedArray()
        builder.setItems(items) { dialog: DialogInterface, which: Int ->
            onSelect(items[which])
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun observeViewModel(root: View) {
        viewModel.filteredRestaurants.observe(viewLifecycleOwner) { restaurants: List<Restaurant> ->
            restaurantAdapter.updateRestaurants(restaurants)
        }

        viewModel.favoriteRestaurants.observe(viewLifecycleOwner) { favorites: Set<String> ->
            restaurantAdapter.setFavoriteRestaurants(favorites)
        }

        viewModel.selectedCategory.observe(viewLifecycleOwner) { category: String ->
            root.findViewById<TextView>(R.id.genreFilterText).text = if (category == "All") "Genre" else category
        }
    }
}


