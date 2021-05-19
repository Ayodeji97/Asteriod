package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Picture of Day interface containing func ready for implementation
 * */
@Dao
interface PictureOfDayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPictureOfDay (pictureOfDay: PicOfDayEntity)

    @Query("SELECT * FROM picture_of_day_table ORDER BY url DESC LIMIT 1")
    fun getPictureOfDay () : LiveData<PicOfDayEntity>

    @Query("DELETE FROM PICTURE_OF_DAY_TABLE")
    suspend fun clearPicOfDay ()

}