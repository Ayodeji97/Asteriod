package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch


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


    private val _status = MutableLiveData<AsteroidApiStatus>()

    private val _pictureOfTheDay = MutableLiveData<PictureOfDay>()


    val status: LiveData<AsteroidApiStatus>
        get() = _status

    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfTheDay


    val asteroidList = asteroidRepository.asteroid
    val pictureOfTheDay = asteroidRepository.pictureOfDay



    init {
        getAllAsteroidAndPicOfDay()
    }


    private fun getAllAsteroidAndPicOfDay () {
        viewModelScope.launch {
            _status.value = AsteroidApiStatus.LOADING
            asteroidRepository.refreshAll()
            _status.value = AsteroidApiStatus.DONE
        }
    }




}






