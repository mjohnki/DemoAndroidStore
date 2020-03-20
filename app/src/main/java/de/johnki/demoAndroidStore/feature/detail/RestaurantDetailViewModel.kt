package de.johnki.demoAndroidStore.feature.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dropbox.android.external.store4.StoreResponse
import de.johnki.demoAndroidStore.repository.Restaurant
import de.johnki.demoAndroidStore.repository.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RestaurantDetailViewModel(private val repository: RestaurantRepository) : ViewModel() {

    val restaurantList = MutableLiveData<Restaurant>()

    fun init(name: String) {
        viewModelScope.launch {
            loadData(name)
        }
    }

    private suspend fun loadData(name: String) {
        withContext(Dispatchers.IO) {
            repository.getRestaurant(name).collect { data ->
                when (data) {
                    is StoreResponse.Data -> restaurantList.postValue(data.value)
                }
            }
        }
    }
}
