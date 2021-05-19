package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository
import retrofit2.HttpException

/**
 * [RefreshDataWork] class responsible for scheduling the pull and caching of data from
 * domain and database respectively
 * */
class RefreshDataWork (appContext : Context, params : WorkerParameters) : CoroutineWorker(appContext, params ) {

    // This is a suspend func and it will run on a background thread
    override suspend fun doWork(): Result {
        val database = AsteroidDatabase.getDatabaseInstance(applicationContext)
        val repository = AsteroidRepository(database)

        return try {
            repository.refreshAsteroids()
            Result.success()

        } catch (e : HttpException) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }


}