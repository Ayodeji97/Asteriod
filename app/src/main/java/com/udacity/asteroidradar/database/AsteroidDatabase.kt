package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 *
 * Asteroid database
 * */
@Database(entities = [AsteroidEntity::class, PicOfDayEntity::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase : RoomDatabase() {

    abstract val asteroidDao : AsteroidDao
    abstract val pictureOfDayDao : PictureOfDayDao

    companion object {

        @Volatile
        private var INSTANCE : AsteroidDatabase? = null

        fun getDatabaseInstance (context: Context) : AsteroidDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDatabase::class.java,
                        "asteroid_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}