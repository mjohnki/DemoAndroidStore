package de.johnki.demoAndroidStore.di

import com.dropbox.android.external.store4.MemoryPolicy
import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreBuilder
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import de.johnki.demoAndroidStore.repository.Restaurant
import de.johnki.demoAndroidStore.repository.RestaurantRepository
import de.johnki.demoAndroidStore.repository.db.AppDatabase
import de.johnki.demoAndroidStore.repository.db.RestaurantDao
import de.johnki.demoAndroidStore.repository.remote.RestaurantApi
import de.johnki.demoAndroidStore.repository.remote.RestaurantEndpoint
import de.johnki.demoAndroidStore.feature.detail.RestaurantDetailFragment
import de.johnki.demoAndroidStore.feature.detail.RestaurantDetailViewModelFactory
import de.johnki.demoAndroidStore.feature.list.RestaurantListFragment
import de.johnki.demoAndroidStore.feature.list.RestaurantListViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@Module(includes = [RestaurantModule.Binding::class])
class RestaurantModule() {

    @Provides
    fun provideRestaurantEndpoint(retrofit: Retrofit): RestaurantEndpoint =
        retrofit.create(RestaurantEndpoint::class.java)

    @Provides
    fun provideRestaurantApi(endpoint: RestaurantEndpoint): RestaurantApi =
        RestaurantApi(endpoint)

    @Provides
    fun provideRestaurantRepository(
        restaurantsStore: Store<String, List<Restaurant>>,
        restaurantStore: Store<String, Restaurant>
    ): RestaurantRepository =
        RestaurantRepository(restaurantsStore, restaurantStore)

    @Provides
    fun provideRestaurantListViewModelFactory(repository: RestaurantRepository): RestaurantListViewModelFactory =
        RestaurantListViewModelFactory(
            repository
        )

    @Provides
    fun provideRestaurantDetailViewModelFactory(repository: RestaurantRepository): RestaurantDetailViewModelFactory =
        RestaurantDetailViewModelFactory(
            repository
        )

    @Provides
    fun provideResturantDao(database: AppDatabase): RestaurantDao =
        database.restaurantDao()

    @Provides
    @FlowPreview
    @ExperimentalCoroutinesApi
    fun provideRestaurantListStore(
        api: RestaurantApi,
        dao: RestaurantDao
    ): Store<String, List<Restaurant>> =
        StoreBuilder
            .fromNonFlow<String, List<Restaurant>> { api.getRestaurants() }
            .cachePolicy(
                MemoryPolicy.builder()
                    .setMemorySize(10)
                    .setExpireAfterWrite(10)
                    .setExpireAfterTimeUnit(TimeUnit.MINUTES)
                    .build()
            ).persister(
                reader = { dao.findAll() },
                writer = { _, list: List<Restaurant> -> dao.insert(list) },
                delete = { dao.clearAll() },
                deleteAll = dao::clearAll
            )
            .build()

    @Provides
    @FlowPreview
    @ExperimentalCoroutinesApi
    fun provideRestaurantStore(
        dao: RestaurantDao
    ): Store<String, Restaurant> =
        StoreBuilder
            .from<String, Restaurant> { dao.findByName(it) }
            .cachePolicy(
                MemoryPolicy.builder()
                    .setMemorySize(10)
                    .setExpireAfterWrite(10)
                    .setExpireAfterTimeUnit(TimeUnit.MINUTES)
                    .build()
            ).build()

    @Module
    abstract class Binding {
        @ContributesAndroidInjector
        abstract fun contributeRestaurantListFragment(): RestaurantListFragment

        @ContributesAndroidInjector
        abstract fun contributeRestaurantDetailFragment(): RestaurantDetailFragment
    }
}