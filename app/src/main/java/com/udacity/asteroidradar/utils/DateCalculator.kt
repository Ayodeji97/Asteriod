package com.udacity.asteroidradar.utils

import java.text.SimpleDateFormat
import java.util.*

object DateCalculator {

    fun getToday () : String {
        val calenderCurrentTime = Calendar.getInstance().time
        val dateFormatter = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormatter.format(calenderCurrentTime)
    }

    fun getNextSevenDay () : String {
        val calendar =  Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 7)
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(currentTime)
    }
}