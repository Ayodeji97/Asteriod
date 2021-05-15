package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.domain.PictureOfDay

@Dao
interface PictureOfDayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPictureOfDay (pictureOfDay: PictureOfDayEntities)

    @Query("SELECT * FROM picture_of_day_table ORDER BY url DESC LIMIT 1")
    fun getPictureOfDay () : LiveData<PictureOfDayEntities>

}