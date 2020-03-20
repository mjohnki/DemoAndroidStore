package de.johnki.demoAndroidStore.repository

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurant(

    @PrimaryKey
    val name: String,
    val presentationImage: String,
    @Embedded
    val location: Location) {

    enum class KitchenType {
        chinese, thai, italian, american, german
    }

    data class Location(
        val lon: String,
        val lat: String)
}
