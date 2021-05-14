package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.api.parseStringToAsteroidList
import com.udacity.asteroidradar.network.NasaApi
import com.udacity.asteroidradar.utils.Constants
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {

    private val _asteroid = MutableLiveData<List<Asteroid>>()
    val asteroid: LiveData<List<Asteroid>>
        get() = _asteroid

    val currentDate = Calendar.getInstance().time
    val formattedDate = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())

    init {
        getAllAsteroid("2015-09-07", "2015-09-09", Constants.API_KEY )
    }

    private fun getAllAsteroid (startDate : String, endDate : String, apiKey : String) {

        viewModelScope.launch {

            try {
                val asteroidList =
                    NasaApi.retrofitService.getAllAsteroids(startDate, endDate, apiKey)
               // val result = parseAsteroidsJsonResult(JSONObject(asteroidList))
                val result = parseStringToAsteroidList(asteroidList)
                Log.i("SEEEEE", "$result")
                println(result)
                _asteroid.value = result
            } catch (e : Exception) {
                print(e.toString())
            }
        }

    }
}