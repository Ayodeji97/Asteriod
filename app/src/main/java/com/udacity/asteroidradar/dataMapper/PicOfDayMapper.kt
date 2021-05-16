package com.udacity.asteroidradar.dataMapper

import com.udacity.asteroidradar.database.PicOfDayEntity
import com.udacity.asteroidradar.model.PictureOfDay

fun PicOfDayEntity.asPicOfDayDomainModel () : PictureOfDay {

    return PictureOfDay(
        mediaType = this.mediaType,
        title = this.title,
        url = this.url
    )

}


fun PictureOfDay.asPicOfDayDatabase () : PicOfDayEntity {
    return PicOfDayEntity(
        mediaType = this.mediaType,
        title = this.title,
        url = this.url
    )
}