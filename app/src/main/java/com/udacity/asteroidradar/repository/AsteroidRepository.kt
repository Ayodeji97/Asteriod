package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.database.asPictureOfDayDomainModel
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.network.NasaApi
import com.udacity.asteroidradar.network.NetworkAsteroid
import com.udacity.asteroidradar.network.asAsteroidDatabaseModel
import com.udacity.asteroidradar.network.asPictureOfDayDatabaseModel
import com.udacity.asteroidradar.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception

class AsteroidRepository (private val asteroidDatabase: AsteroidDatabase) {

    val asteroid : LiveData<List<Asteroid>> = Transformations.map(asteroidDatabase.asteroidDao.getAsteroids()) {
        it.asDomainModel()
    }

    val pictureOfDay : LiveData<PictureOfDay> = Transformations.map(asteroidDatabase.pictureOfDayDao.getPictureOfDay()) {
        it?.asPictureOfDayDomainModel()
    }


    suspend fun refreshAsteroids () {
        withContext(context = Dispatchers.IO) {

            try {

                val asteroid = NasaApi.retrofitService.getAllAsteroids("2021-05-14", "2021-05-16", Constants.API_KEY)

                val parsedAsteroids = parseAsteroidsJsonResult(JSONObject(asteroid))

                val networkAsteroidList = parsedAsteroids.map {
                    NetworkAsteroid(
                        it.id,
                        it.codename,
                        it.closeApproachDate,
                        it.absoluteMagnitude,
                        it.estimatedDiameter,
                        it.relativeVelocity,
                        it.distanceFromEarth,
                        it.isPotentiallyHazardous
                    )
                }

                asteroidDatabase.asteroidDao.insertAll(*networkAsteroidList.asAsteroidDatabaseModel().toTypedArray())

            } catch (e : Exception) {
                Log.i("INSERTING_ERROR","Error inserting asteroid into database: $e")
            }

        }
    }


    suspend fun refreshPictureOfDay () {
        withContext(Dispatchers.IO) {

            try {
                val pictureOfDay = NasaApi.imageOfTheDayRetrofitService.getNasaImageOfTheDay(Constants.API_KEY)

                pictureOfDay.let {
                    asteroidDatabase.pictureOfDayDao.insertPictureOfDay(it?.asPictureOfDayDatabaseModel())
                }

            } catch (e : Exception) {

                Log.i("INSERTING_PICTURE_ERROR","Error inserting picture into database: $e")
            }

        }
    }
}