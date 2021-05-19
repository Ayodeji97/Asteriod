package com.udacity.asteroidradar.model

import com.squareup.moshi.Json

/**
 * Picture Model
 * */
data class PictureOfDay(
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String
)