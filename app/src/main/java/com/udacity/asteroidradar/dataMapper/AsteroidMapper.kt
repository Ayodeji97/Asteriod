package com.udacity.asteroidradar.dataMapper

import com.udacity.asteroidradar.database.AsteroidEntity
import com.udacity.asteroidradar.model.Asteroid

/**
 * Asteroid mapper extension function to map database model to domain model
 * */

fun List<AsteroidEntity>.asAsteroidDomainModel() : List<Asteroid> {

    return map {asteroidEntity ->
        Asteroid(
            id = asteroidEntity.id,
            codename = asteroidEntity.codename,
            absoluteMagnitude = asteroidEntity.absoluteMagnitude,
            estimatedDiameter = asteroidEntity.estimatedDiameter,
            relativeVelocity = asteroidEntity.relativeVelocity,
            distanceFromEarth = asteroidEntity.distanceFromEarth,
            closeApproachDate = asteroidEntity.closeApproachDate,
            isPotentiallyHazardous = asteroidEntity.isPotentiallyHazardous
        )
    }
}


/**
 * Asteroid mapper extension function to map domain model to database model
 * */

fun List<Asteroid>.asAsteroidDatabaseModel() : Array<AsteroidEntity> {

    return map {asteroid ->
        AsteroidEntity(
            id = asteroid.id,
            codename = asteroid.codename,
            absoluteMagnitude = asteroid.absoluteMagnitude,
            estimatedDiameter = asteroid.estimatedDiameter,
            relativeVelocity = asteroid.relativeVelocity,
            distanceFromEarth = asteroid.distanceFromEarth,
            closeApproachDate = asteroid.closeApproachDate,
            isPotentiallyHazardous = asteroid.isPotentiallyHazardous
        )

    }.toTypedArray()
}