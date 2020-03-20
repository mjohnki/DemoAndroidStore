package de.johnki.demoAndroidStore.repository

import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreRequest
import com.dropbox.android.external.store4.StoreResponse
import kotlinx.coroutines.flow.Flow

class RestaurantRepository(
    private val restaurantsStore: Store<String, List<Restaurant>>,
    private val restaurantStore: Store<String, Restaurant>
) {

    fun getRestaurants(): Flow<StoreResponse<List<Restaurant>>> =
        restaurantsStore.stream(StoreRequest.cached(key = KEY, refresh = true))

    fun getRestaurant(name: String): Flow<StoreResponse<Restaurant>> =
        restaurantStore.stream(StoreRequest.cached(key = name, refresh = true))

    companion object {
        private const val KEY = "RestaurantList"
    }
}