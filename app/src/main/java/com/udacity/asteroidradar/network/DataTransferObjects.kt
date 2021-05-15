package com.udacity.asteroidradar.network


import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.database.AsteroidDatabaseEntities
import com.udacity.asteroidradar.database.PictureOfDayEntities
import com.udacity.asteroidradar.domain.PictureOfDay

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
data class NetworkPictureOfDay(
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,
    @PrimaryKey
    val url: String
)


/**
 * Extension function that convert from network object to database objects
 * */
fun PictureOfDay.asPictureOfDayDatabaseModel () : PictureOfDayEntities {
    return PictureOfDayEntities(
        mediaType = this.mediaType,
        title = this.title,
        url = this.url
    )

}

/**
 * Extension function that convert from network object to database objects
 * */

fun List<NetworkAsteroid>.asAsteroidDatabaseModel () : List<AsteroidDatabaseEntities> {
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


