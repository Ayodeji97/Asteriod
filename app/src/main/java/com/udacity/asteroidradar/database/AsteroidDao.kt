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

   @Query("SELECT * from asteroid_table ORDER BY id DESC")
    fun getAsteroids () : LiveData<List<AsteroidDatabaseEntities>>


    /**
     * Return the latest asteroid
     * */
    @Query("SELECT * FROM asteroid_table ORDER BY id DESC LIMIT 1")
    suspend fun getTodayAsteroid () : AsteroidDatabaseEntities?

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     * */
    @Query("DELETE FROM asteroid_table")
    suspend fun clear()


    @Query("SELECT * FROM asteroid_table WHERE id = :key")
    fun getAsteroidWithId(key : Long) : LiveData<List<AsteroidDatabaseEntities>>




}