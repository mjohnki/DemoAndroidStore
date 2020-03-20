package de.johnki.demoAndroidStore.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.johnki.demoAndroidStore.repository.RestaurantRepository

class RestaurantDetailViewModelFactory(
    private val repository: RestaurantRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        RestaurantDetailViewModel(repository) as T
}
