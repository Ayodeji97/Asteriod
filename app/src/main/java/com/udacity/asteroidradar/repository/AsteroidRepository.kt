package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.dataMapper.asAsteroidDatabaseModel
import com.udacity.asteroidradar.dataMapper.asAsteroidDomainModel
import com.udacity.asteroidradar.dataMapper.asPicOfDayDatabase
import com.udacity.asteroidradar.dataMapper.asPicOfDayDomainModel
import com.udacity.asteroidradar.network.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.network.NasaApi
import com.udacity.asteroidradar.utils.Constants
import com.udacity.asteroidradar.utils.Constants.API_KEY
import com.udacity.asteroidradar.utils.DateCalculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception

/**
 * [AsteroidRepository] : Normal class which serves as a playground for both domain and database data interaction and abstraction
 * */
class AsteroidRepository (private val asteroidDatabase: AsteroidDatabase) {

    /**
     * Get the current date
     * */
        private var currentDate = DateCalculator.getToday()
    /**
     * Get the next seven day date
     * */
        private var endDate = DateCalculator.getNextSevenDay()


    /**
     * ASTEROID PROPERTIES AND METHODS
     * */
    val allAsteroids : LiveData<List<Asteroid>> =
        Transformations.map(asteroidDatabase.asteroidDao.getAsteroids()) {
        it.asAsteroidDomainModel()
    }


    /**
     * Get today asteroids
     * */
    val todayAsteroids : LiveData<List<Asteroid>> =
        Transformations.map(asteroidDatabase.asteroidDao.getTodayAsteroids(currentDate)) {
            it.asAsteroidDomainModel()
        }

    /**
     * Get week asteroids
     * */
    val weekAsteroids : LiveData<List<Asteroid>> =
        Transformations.map(asteroidDatabase.asteroidDao.getWeekAsteroids(currentDate, endDate)) {
            it.asAsteroidDomainModel()
        }


    /**
     * Suspend func to get asteroid from server and cache to database
     * */
    suspend fun refreshAsteroids () {
        withContext(context = Dispatchers.IO) {

                try {

                    //val apiKey = BuildConfig.API_KEY
                    val asteroids = NasaApi.retrofitService.getAllAsteroids(currentDate, endDate, Constants.API_KEY)

                    val parsedAsteroids = parseAsteroidsJsonResult(JSONObject(asteroids))

                    asteroidDatabase.asteroidDao.insertAll(*parsedAsteroids.asAsteroidDatabaseModel())


                } catch (e : Exception) {
                    Log.e("INSERTION ERROR", "Error inserting domain asteroids : ${e.localizedMessage}")
                }
        }
    }


    /**
     * PICTURE OF DAY PROPERTIES AND FUNCTION
     * */

    val picOfDay : LiveData<PictureOfDay> = Transformations.map(asteroidDatabase.pictureOfDayDao.getPictureOfDay()) {
        it?.asPicOfDayDomainModel()
    }


    /**
     * Suspend func to get picture from server and cache to database
     * */
  suspend fun refreshPictureOfDay () {

        withContext(Dispatchers.IO) {

            try {
                val picOfDay = NasaApi.retrofitService.getNasaImageOfTheDay(Constants.API_KEY)

                picOfDay.let {
                    asteroidDatabase.pictureOfDayDao.insertPictureOfDay(picOfDay.asPicOfDayDatabase())
                }

            }
            catch (e : Exception) {
                Log.e("PICTUREINSERTIONERROR", "Error inserting domain picOfDay : ${e.localizedMessage}")
            }
        }
    }

}