package de.johnki.demoAndroidStore.feature.list

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

class RestaurantListViewModel(private val repository: RestaurantRepository) : ViewModel() {

    val restaurantList = MutableLiveData<List<Restaurant>>()

    fun init(){
        viewModelScope.launch {
            loadData()
        }
    }

    private suspend fun loadData() {
        withContext(Dispatchers.IO) {
            repository.getRestaurants().collect { data ->
                when (data) {
                    is StoreResponse.Data -> restaurantList.postValue(data.value)
                }
            }
        }
    }
}
