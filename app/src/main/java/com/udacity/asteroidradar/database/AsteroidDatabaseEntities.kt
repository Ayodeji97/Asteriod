package com.udacity.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.domain.Asteroid

@Entity(tableName = "asteroid_table")
data class AsteroidDatabaseEntities (
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
 * Extension function which convert from database objects to domain objects (network object)
 * or converting from database objects to model object in the network package
 * */

fun List<AsteroidDatabaseEntities>.asDomainModel() : List<Asteroid> {

    return map {asteroidInDatabase ->
        Asteroid(
            id = asteroidInDatabase.id,
            codename = asteroidInDatabase.codename,
            closeApproachDate = asteroidInDatabase.closeApproachDate,
            absoluteMagnitude = asteroidInDatabase.absoluteMagnitude,
            estimatedDiameter = asteroidInDatabase.estimatedDiameter,
            relativeVelocity = asteroidInDatabase.relativeVelocity,
            distanceFromEarth = asteroidInDatabase.distanceFromEarth,
            isPotentiallyHazardous = asteroidInDatabase.isPotentiallyHazardous

        )
    }
}