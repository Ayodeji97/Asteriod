package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll (vararg asteroids : AsteroidDatabaseEntities)

   @Query("SELECT * from asteroid_table")
    fun getAsteroids () : LiveData<List<AsteroidDatabaseEntities>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPictureOfDay (pictureOfDayEntities: PictureOfDayEntities)



}