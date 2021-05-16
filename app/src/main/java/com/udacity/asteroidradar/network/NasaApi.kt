package com.udacity.asteroidradar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .readTimeout(60, TimeUnit.SECONDS)
    .connectTimeout(60, TimeUnit.SECONDS)
    .build()


private val retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

//private val asteroidRetrofit = Retrofit.Builder()
//    .baseUrl(Constants.BASE_URL)
//    .addConverterFactory(ScalarsConverterFactory.create())
//    .build()


object NasaApi {

    val retrofitService : NasaApiService by lazy {
       retrofit.create(NasaApiService::class.java)
    }

//    val imageOfTheDayRetrofitService : PictureOfDayService by lazy {
//        retrofit.create(PictureOfDayService::class.java)
//    }
}

