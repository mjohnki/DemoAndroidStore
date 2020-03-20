package de.johnki.demoAndroidStore.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import de.johnki.demoAndroidStore.repository.Restaurant

@Database(entities = [Restaurant::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}