package com.example.foodapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple pager adapter for ViewPager2 that shows drawable images (Int resource IDs).
 * Usage:
 *   val adapter = ImagePagerAdapter(restaurant.imageGallery) { index ->
 *       // optional: open full-screen viewer for image at 'index'
 *   }
 *   viewPager.adapter = adapter
 */
class ImagePagerAdapter(
    private var images: List<Int>, //store the drawable's ID
    private val onImageClick: ((position: Int) -> Unit)? = null // passes the position of the clicked image
) : RecyclerView.Adapter<ImagePagerAdapter.ImageVH>() {

    class ImageVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.pagerImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pager_image, parent, false)
        return ImageVH(view)
    }

    override fun onBindViewHolder(holder: ImageVH, position: Int) {
        // Set image resource (drawable ID)
        holder.image.setImageResource(images[position])

        // Optional: contentDescription for accessibility
        holder.image.contentDescription = "Restaurant image ${position + 1} of ${images.size}"

        // Optional: click to open full-screen (if provided)
        holder.image.setOnClickListener { onImageClick?.invoke(position) }
    }

    override fun getItemCount(): Int = images.size

    /** If you ever need to refresh the pager’s dataset */
    fun submitImages(newImages: List<Int>) {
        images = newImages
        notifyDataSetChanged()
    }
}
