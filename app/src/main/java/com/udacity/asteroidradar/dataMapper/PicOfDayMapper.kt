package com.udacity.asteroidradar.dataMapper

import com.udacity.asteroidradar.database.PicOfDayEntity
import com.udacity.asteroidradar.model.PictureOfDay

/**
 * Picture mapper extension function to map database model to domain model
 * */
fun PicOfDayEntity.asPicOfDayDomainModel () : PictureOfDay {
    return PictureOfDay(
        mediaType = this.mediaType,
        title = this.title,
        url = this.url
    )

}


/**
 * Picture mapper extension function to map domain model to database model
 * */
fun PictureOfDay.asPicOfDayDatabase () : PicOfDayEntity {
    return PicOfDayEntity(
        mediaType = this.mediaType,
        title = this.title,
        url = this.url
    )
}