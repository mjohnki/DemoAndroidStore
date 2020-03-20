package de.johnki.demoAndroidStore.repository.remote

import de.johnki.demoAndroidStore.repository.Restaurant

class RestaurantApi(private val endpoint: RestaurantEndpoint) {

    suspend fun getRestaurants(): List<Restaurant> =
        endpoint.getRestaurants()

}