package com.udacity.asteroidradar.network

import retrofit2.http.GET
import retrofit2.http.Query



interface NasaApiService {
    @GET("neo/rest/v1/feed")
    suspend fun getAllAsteroids (
        @Query("start_date") startDate : String,
        @Query("end_date") endDate : String,
        @Query("api_key") apiKey : String
    ) : String
}





