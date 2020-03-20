package de.johnki.demoAndroidStore.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.johnki.demoAndroidStore.repository.RestaurantRepository

class RestaurantListViewModelFactory(
    private val repository: RestaurantRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        RestaurantListViewModel(repository) as T
}
