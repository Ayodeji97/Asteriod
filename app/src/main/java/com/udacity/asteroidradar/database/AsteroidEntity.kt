package com.udacity.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Asteroid Entity to mapper to network entity
 * */
@Entity(tableName = "asteroid_table")
data class AsteroidEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)