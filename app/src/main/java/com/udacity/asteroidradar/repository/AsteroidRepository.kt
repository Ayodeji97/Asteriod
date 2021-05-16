package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.dataMapper.asAsteroidDatabaseModel
import com.udacity.asteroidradar.dataMapper.asAsteroidDomainModel
import com.udacity.asteroidradar.network.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asPictureOfDayDomainModel
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.network.NasaApi
import com.udacity.asteroidradar.utils.AsteroidFilters
import com.udacity.asteroidradar.utils.Constants
import com.udacity.asteroidradar.utils.DateCalculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
import java.util.*

class AsteroidRepository (private val asteroidDatabase: AsteroidDatabase) {

    /**
     * Get the current date
     * */

        private var currentDate = DateCalculator.getToday()
        private var endDate = DateCalculator.getNextSevenDay()



    val allAsteroids : LiveData<List<Asteroid>> =
        Transformations.map(asteroidDatabase.asteroidDao.getAsteroids()) {
        it.asAsteroidDomainModel()
    }


    val todayAsteroids : LiveData<List<Asteroid>> =
        Transformations.map(asteroidDatabase.asteroidDao.getTodayAsteroids(currentDate)) {
            it.asAsteroidDomainModel()
        }

    val weekAsteroids : LiveData<List<Asteroid>> =
        Transformations.map(asteroidDatabase.asteroidDao.getWeekAsteroids(currentDate, endDate)) {
            it.asAsteroidDomainModel()
        }



    suspend fun refreshAsteroids () {
        withContext(context = Dispatchers.IO) {

            withContext(Dispatchers.IO) {

                try {

                    val asteroids = NasaApi.retrofitService.getAllAsteroids(currentDate, endDate, Constants.API_KEY)

                    val parsedAsteroids = parseAsteroidsJsonResult(JSONObject(asteroids))

                    asteroidDatabase.asteroidDao.insertAll(*parsedAsteroids.asAsteroidDatabaseModel())


                } catch (e : Exception) {
                    Log.e("INSERTION ERROR", "Error inserting domain asteroids : $e")
                }
            }

        }
    }


  private suspend fun refreshPictureOfDay () {


    }


//    suspend fun refreshAll () {
//        withContext(Dispatchers.IO) {
//            refreshAsteroids()
//            refreshPictureOfDay()
//        }
//    }
}