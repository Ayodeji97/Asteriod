package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.network.NasaApi
import com.udacity.asteroidradar.network.NasaApiService
import com.udacity.asteroidradar.repository.AsteroidRepository
import com.udacity.asteroidradar.utils.AsteroidFilters
import com.udacity.asteroidradar.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


enum class AsteroidApiStatus {
    LOADING,
    DONE,
    ERROR
}

class MainViewModel(application: Application) : AndroidViewModel(application) {


    // Get reference to database instance
    private val database = AsteroidDatabase.getDatabaseInstance(application)

    // Get reference to repository
    private val asteroidRepository = AsteroidRepository(database)


    private val _asteroidFilter = MutableLiveData(AsteroidFilters.SHOW_TODAY)


    private val _status = MutableLiveData<AsteroidApiStatus>()

    private val _pictureOfTheDay = MutableLiveData<PictureOfDay>()


    val status: LiveData<AsteroidApiStatus>
        get() = _status

    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfTheDay




    val asteroidList = Transformations.switchMap(_asteroidFilter) {input: AsteroidFilters? ->
        when (input) {
            AsteroidFilters.SHOW_TODAY -> asteroidRepository.todayAsteroids
            AsteroidFilters.SHOW_WEEK -> asteroidRepository.weekAsteroids
            else -> asteroidRepository.allAsteroids
        }

    }

    val picOfDay = asteroidRepository.picOfDay


    init {
        viewModelScope.launch {
            asteroidRepository.refreshAsteroids()
            asteroidRepository.refreshPictureOfDay()
            refreshPicOfDay()
        }
    }

    fun onClickedFilter (filters: AsteroidFilters) {
        _asteroidFilter.postValue(filters)
    }

    private suspend fun refreshPicOfDay () {
        withContext(Dispatchers.IO) {

            try {
                _pictureOfTheDay.postValue(
                    NasaApi.retrofitService.getNasaImageOfTheDay(Constants.API_KEY)
                )
            }
            catch (e : Exception) {
                Log.e("refreshPictureOfDay", e.printStackTrace().toString())
            }
        }
    }



    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }



}






