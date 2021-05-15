package com.udacity.asteroidradar.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.database.AsteroidDatabase
import kotlinx.android.parcel.Parcelize

data class Asteroid (
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
