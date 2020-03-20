package de.johnki.demoAndroidStore.repository.db

import androidx.room.*
import de.johnki.demoAndroidStore.repository.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant WHERE name LIKE :name")
    fun findByName(name: String): Flow<Restaurant>

    @Query("SELECT * FROM restaurant")
    fun findAll(): Flow<List<Restaurant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurant: List<Restaurant>)

    @Query("DELETE FROM restaurant")
    suspend fun clearAll()
}