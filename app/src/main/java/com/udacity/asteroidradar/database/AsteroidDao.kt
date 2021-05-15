package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.domain.Asteroid

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll (vararg asteroid: Asteroid)

   @Query("SELECT * from asteroid_table")
    suspend fun getAsteroids () : LiveData<String>

}