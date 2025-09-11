package com.example.foodapp

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Color

// This Fragment renders the main restaurant list screen, wires search and filters to the ViewModel,
// and navigates to RestaurantDetailFragment when a list item is tapped.
class MainListFragment : Fragment(R.layout.activity_main_screen) {

    // We declare references to the views we will interact with so we can set listeners and update text.
    private lateinit var searchView: SearchView
    private lateinit var favouritesButton: LinearLayout
    private lateinit var genreFilterCard: CardView
    private lateinit var locationFilterCard: CardView
    private lateinit var priceFilterCard: CardView
    private lateinit var ratingFilterCard: CardView
    private lateinit var restaurantRecyclerView: RecyclerView

    private lateinit var favoriteBtnIcon: ImageView

    private lateinit var favoriteBtnText: TextView

    // The adapter binds Restaurant data to RecyclerView rows and exposes click callbacks.
    private lateinit var restaurantAdapter: RestaurantAdapter

    // The Activity-scoped ViewModel owns all state (search, filters, favorites) and survives rotation.
    private lateinit var viewModel: RestaurantViewModel

    // These lists feed the filter dialogs; choosing "All" clears a filter or disables sorting.
    private val genres = listOf("All", "Asian", "Local", "Fusion", "Halal", "Western", "Vegetarian", "Grill", "Malaysian", "Chinese", "Indian", "Vietnamese", "Japanese")
    private val locations = listOf("All", "Riam", "Pujut", "Pelita", "Senadin", "Krokop", "Marina", "Boulevard", "Permaisuri", "Sun City", "Miri Times Square")
    private val priceRanges = listOf("All", "Highest", "Lowest") // This controls price sorting.
    private val ratings = listOf("All", "Highest", "Lowest")     // This controls rating sorting.

    // onViewCreated is where we bind the layout views, obtain the shared ViewModel, and attach observers.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1) We find all views from the inflated layout so we can register listeners and update text.
        searchView = view.findViewById(R.id.searchView)
        favouritesButton = view.findViewById(R.id.favouritesButton)
        favoriteBtnIcon = view.findViewById(R.id.favouriteIcon)
        favoriteBtnText = view.findViewById(R.id.favouriteText)
        genreFilterCard = view.findViewById(R.id.genreFilterCard)
        locationFilterCard = view.findViewById(R.id.locationFilterCard)
        priceFilterCard = view.findViewById(R.id.priceFilterCard)
        ratingFilterCard = view.findViewById(R.id.ratingFilterCard)
        restaurantRecyclerView = view.findViewById(R.id.restaurantRecyclerView)

        // 2) We obtain the Activity-scoped ViewModel so this state is shared with the detail fragment and survives rotation.
        viewModel = ViewModelProvider(requireActivity())[RestaurantViewModel::class.java]

        // 3) We construct the adapter and provide two lambdas: one for opening details and one for toggling favorites.
        restaurantAdapter = RestaurantAdapter(
            restaurants = emptyList(), // We start empty; LiveData will push the initial full list.
            onItemClick = { restaurant ->
                // We navigate to the detail Fragment so both screens share the same Activity-scoped ViewModel.
                // We dynamically obtain the container ID by asking for this fragment view's parent ID,
                // which is the FrameLayout/FragmentContainerView hosting this fragment.
                val containerId = (requireView().parent as? ViewGroup)?.id
                    ?: error("Host container must have an ID to perform fragment transactions.")

                parentFragmentManager
                    .beginTransaction()
                    .replace(
                        containerId,
                        RestaurantDetailFragment.newInstance(restaurant) // We pass the restaurant as a Fragment argument (Parcelable).
                    )
                    .addToBackStack(null) // We add to the back stack so the system back button returns to this list.
                    .commit()
            },
            onFavoriteClick = { restaurant, _ ->
                // We toggle the favorite inside the shared ViewModel so icons and filters update everywhere.
                viewModel.toggleFavorite(restaurant.title)
            }
        )

        // 4) We connect the RecyclerView to a vertical LinearLayoutManager and attach the adapter.
        restaurantRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = restaurantAdapter
        }

        // 5) We wire up the SearchView and the filter cards to push changes into the ViewModel.
        setupSearchView()
        setupFilterButtons(view)

        // 6) We observe LiveData from the ViewModel so the UI updates automatically whenever state changes.
        observeViewModel(view)

        // 7) We restore visible labels and the search text from the current ViewModel values for good UX after rotation.
        view.findViewById<TextView>(R.id.genreFilterText).text =
            viewModel.selectedCategory.value?.let { if (it == "All") "Genre" else it } ?: "Genre"
        view.findViewById<TextView>(R.id.locationFilterText).text =
            viewModel.selectedLocation.value?.let { if (it == "All") "Location" else it } ?: "Location"
        view.findViewById<TextView>(R.id.priceFilterText).text =
            viewModel.priceFilter.value?.let { if (it == "All") "Price" else it } ?: "Price"
        view.findViewById<TextView>(R.id.ratingFilterText).text =
            viewModel.ratingFilter.value?.let { if (it == "All") "Rating" else it } ?: "Rating"
        searchView.setQuery(viewModel.searchQuery.value ?: "", false)
    }

    // This method makes the SearchView filter the list as the user types by updating the ViewModel on each change.
    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // We do not act on submit because we filter in real time while typing.
            override fun onQueryTextSubmit(query: String?): Boolean = false

            // Every change to the search text is sent to the ViewModel, which recomputes the filtered list.
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateSearchQuery(newText ?: "")
                return true
            }
        })
    }

    // This method registers click listeners for the four filter cards and opens a dialog for each to choose an option.
    private fun setupFilterButtons(root: View) {
        // Tapping this toggles the "show only favourites" mode and triggers a recomputation of the list.
        favouritesButton.setOnClickListener {
            viewModel.showFavoritesOnly()
        }

        // Each filter card opens a dialog and updates the ViewModel with the selected value.
        genreFilterCard.setOnClickListener {
            showFilterDialog("Genre", genres) { selected ->
                viewModel.updateSelectedCategory(selected)
                root.findViewById<TextView>(R.id.genreFilterText).text = if (selected == "All") "Genre" else selected
            }
        }

        locationFilterCard.setOnClickListener {
            showFilterDialog("Location", locations) { selected ->
                viewModel.updateLocationFilter(selected)
                root.findViewById<TextView>(R.id.locationFilterText).text = if (selected == "All") "Location" else selected
            }
        }

        priceFilterCard.setOnClickListener {
            showFilterDialog("Price", priceRanges) { selected ->
                viewModel.updatePriceFilter(selected)
                root.findViewById<TextView>(R.id.priceFilterText).text = if (selected == "All") "Price" else selected
            }
        }

        ratingFilterCard.setOnClickListener {
            showFilterDialog("Rating", ratings) { selected ->
                viewModel.updateRatingFilter(selected)
                root.findViewById<TextView>(R.id.ratingFilterText).text = if (selected == "All") "Rating" else selected
            }
        }
    }

    // This helper shows a simple list dialog and calls back with the chosen string so we can update state and labels.
    private fun showFilterDialog(title: String, options: List<String>, onSelect: (String) -> Unit) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select $title")

        val items = options.toTypedArray()
        builder.setItems(items) { dialog: DialogInterface, which: Int ->
            onSelect(items[which]) // We immediately deliver the selected value to the caller-provided callback.
            dialog.dismiss()       // We close the dialog once a selection is made.
        }

        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }

    // These observers keep the list and the tiny labels in sync with the ViewModel’s LiveData.
    private fun observeViewModel(root: View) {
        // Whenever the filtered list changes, we replace the adapter list and refresh the RecyclerView.
        viewModel.filteredRestaurants.observe(viewLifecycleOwner) { restaurants ->
            restaurantAdapter.updateRestaurants(restaurants)
        }

        // Whenever the favourites set changes, we pass it to the adapter so the heart icons update.
        viewModel.favoriteRestaurants.observe(viewLifecycleOwner) { favorites ->
            restaurantAdapter.setFavoriteRestaurants(favorites)
        }

        // Handle the display of the favourite icon, to indicate whether it is showing only favourite restaurants or not
        viewModel.showFavoritesOnly.observe(viewLifecycleOwner) { showFavorites ->
            if (showFavorites) {
                // Red = active state
                favoriteBtnIcon.setImageResource(R.drawable.ic_favourite_filled)
                favoriteBtnText.setTextColor(Color.RED)
            } else {
                // Brownish = default state/ show all restaurants
                favoriteBtnIcon.setImageResource(R.drawable.ic_favourite_filled_brown)
                favoriteBtnText.setTextColor(Color.parseColor("#CC9767"))
            }
        }

        // These keep the small text labels on the filter cards aligned with the current filter values.
        viewModel.selectedCategory.observe(viewLifecycleOwner) { category ->
            root.findViewById<TextView>(R.id.genreFilterText).text = if (category == "All") "Genre" else category
        }
        viewModel.selectedLocation.observe(viewLifecycleOwner) { location ->
            root.findViewById<TextView>(R.id.locationFilterText).text = if (location == "All") "Location" else location
        }
        viewModel.priceFilter.observe(viewLifecycleOwner) { price ->
            root.findViewById<TextView>(R.id.priceFilterText).text = if (price == "All") "Price" else price
        }
        viewModel.ratingFilter.observe(viewLifecycleOwner) { rating ->
            root.findViewById<TextView>(R.id.ratingFilterText).text = if (rating == "All") "Rating" else rating
        }
    }
}
