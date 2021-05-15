package com.udacity.asteroidradar.network

import com.udacity.asteroidradar.domain.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query


interface PictureOfDayService {
    @GET("planetary/apod")
    suspend fun getNasaImageOfTheDay (
        @Query("api_key") apiKey : String
    ) : PictureOfDay
}