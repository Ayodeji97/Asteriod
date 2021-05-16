package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll (vararg asteroids : AsteroidEntity)

   @Query("SELECT * from asteroid_table ORDER BY closeApproachDate DESC")
    fun getAsteroids () : LiveData<List<AsteroidEntity>>

    @Query("SELECT * from asteroid_table WHERE closeApproachDate = :currentDate ORDER BY closeApproachDate DESC")
    fun getTodayAsteroids (currentDate : String) : LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroid_table WHERE closeApproachDate BETWEEN :currentDate AND :endDate ORDER BY closeApproachDate DESC")
    fun getWeekAsteroids (currentDate : String, endDate : String) : LiveData<List<AsteroidEntity>>

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     * */
    @Query("DELETE FROM asteroid_table")
    suspend fun clear()


    @Query("SELECT * FROM asteroid_table WHERE id = :key")
    fun getAsteroidWithId(key : Long) : LiveData<List<AsteroidEntity>>




}