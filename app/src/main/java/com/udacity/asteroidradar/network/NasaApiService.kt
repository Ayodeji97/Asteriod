package com.udacity.asteroidradar.network

import com.udacity.asteroidradar.model.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * [NasaApiService] for func to implement to get all asteroids and image of day from domain
 * */
interface NasaApiService {
    @GET("neo/rest/v1/feed")
    suspend fun getAllAsteroids (
        @Query("start_date") startDate : String,
        @Query("end_date") endDate : String,
        @Query("api_key") apiKey : String
    ) : String

    @GET("planetary/apod")
    suspend fun getNasaImageOfTheDay (
        @Query("api_key") apiKey : String
    ) : PictureOfDay
}







