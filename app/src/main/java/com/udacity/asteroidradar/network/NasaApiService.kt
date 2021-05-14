package com.udacity.asteroidradar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()



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

object NasaApi {
    val retrofitService : NasaApiService by lazy {
        retrofit.create(NasaApiService::class.java)
    }

    val imageOfTheDayRetrofitService : NasaApiService by lazy {
        retrofit.create(NasaApiService::class.java)
    }
}





