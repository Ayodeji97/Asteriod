package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay

@Database(entities = [AsteroidDatabaseEntities::class, PictureOfDayEntities::class], version = 1, exportSchema = false)
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