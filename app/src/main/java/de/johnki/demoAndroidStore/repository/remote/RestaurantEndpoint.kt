package de.johnki.demoAndroidStore.repository.remote

import de.johnki.demoAndroidStore.repository.Restaurant
import retrofit2.http.GET

interface RestaurantEndpoint {

    @GET(value = "restaurants")
    suspend fun getRestaurants(): List<Restaurant>

}