package de.johnki.demoAndroidStore.feature.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.johnki.demoAndroidFlow.R
import de.johnki.demoAndroidStore.images.loadUrl
import de.johnki.demoAndroidStore.repository.Restaurant
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_restaurant.*

class RestaurantListAdapter : ListAdapter<Restaurant, RestaurantViewHolder>(
    DiffCallback()
) {

    var listener: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RestaurantViewHolder.create(
            parent
        )

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }
}

class RestaurantViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView), LayoutContainer {

    override val containerView: View?
        get() = itemView

    fun bind(
        restaurant: Restaurant,
        listener: (String) -> Unit
    ) {
        content.setOnClickListener { listener(restaurant.name) }
        restaurantName.text = restaurant.name
        restaurantImage.loadUrl(restaurant.presentationImage)
    }

    companion object {
        fun create(parent: ViewGroup) =
            RestaurantViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
            )
    }
}