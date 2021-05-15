package com.udacity.asteroidradar.network

import androidx.lifecycle.Transformations.map
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.database.AsteroidDatabaseEntities
import com.udacity.asteroidradar.domain.Asteroid

@JsonClass(generateAdapter = true)
data class NetworkAsteroidContainer (
    val asteroid : List<NetworkAsteroid>
)


/**
 * Representing an asteroid in kotlin object for json data
 * */
@JsonClass(generateAdapter = true)
data class NetworkAsteroid constructor(
    @PrimaryKey
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)



/**
 * Representing a picture in kotlin object for json data
 * */
@JsonClass(generateAdapter = true)
data class PictureOfDay(
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,
    @PrimaryKey
    val url: String
)




/**
 * Extension function that convert from network object to database objects
 * */

fun List<NetworkAsteroid>.asDatabaseModel () : List<AsteroidDatabaseEntities> {
    return map {networkAsteroid ->
        AsteroidDatabaseEntities(
            id = networkAsteroid.id,
            codename = networkAsteroid.codename,
            closeApproachDate = networkAsteroid.closeApproachDate,
            absoluteMagnitude = networkAsteroid.absoluteMagnitude,
            estimatedDiameter = networkAsteroid.estimatedDiameter,
            relativeVelocity = networkAsteroid.relativeVelocity,
            distanceFromEarth = networkAsteroid.distanceFromEarth,
            isPotentiallyHazardous = networkAsteroid.isPotentiallyHazardous
        )
    }
}


