package com.example.foodapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val title: String,
    val description: String,
    val phoneNumber: String,
    val categories: List<String>,
    val price: String,
    val mealType: String,
    val imageGallery: List<Int>,
    val address: String,
    val mapsUrl: String, //Integrate it with google Maps, if got extra time
    val hours: Map<String, String>,
    val rating: Float = 4.0f,
    val reviewCount : Int = 1000,
    val priceLevel: Int = 2
) : Parcelable
