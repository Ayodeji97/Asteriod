package com.udacity.asteroidradar.network

import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.database.AsteroidDatabaseEntities
import com.udacity.asteroidradar.domain.Asteroid

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
 * Extension function that convert from network object to database objects
 * */

fun NetworkAsteroidContainer.asDatabaseModel () : List<AsteroidDatabaseEntities> {

    return asteroid.map {networkAsteroid ->
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