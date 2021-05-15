package com.udacity.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.network.NetworkPictureOfDay


@Entity(tableName = "picture_of_day_table")
data class PictureOfDayEntities constructor(
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,
    @PrimaryKey
    val url: String
)


/**
 * map database data to network
 * */

fun PictureOfDayEntities.asPictureOfDayDomainModel () : PictureOfDay {
    return PictureOfDay(
        mediaType = this.mediaType,
        title = this.title,
        url = this.url
    )
}
