package com.udacity.asteroidradar.utils

/**
 * Enumeration class for different way to filter asteroids to render on the screen
 * */
enum class AsteroidFilters(val value : String) {
    SHOW_WEEK("week"),
    SHOW_TODAY("today"),
    SHOW_SAVE("saved")
}