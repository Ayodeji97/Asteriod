package com.udacity.asteroidradar.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}


@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}


/**
 * Asteroid name
 * **/
@BindingAdapter("asteroidName")
fun TextView.setAsteroidName (item : Asteroid) {
    item?.let {
        text = item.codename
    }
}

/**
 * Close Approach date
 * */
@BindingAdapter("asteroidDate")
fun TextView.setAsteroidData (item : Asteroid) {
    item?.let {
        text = item.closeApproachDate
    }
}


/**
 * Status emoji to display
 * */
@BindingAdapter("bindStatus")
fun ImageView.bindStatus (item: Asteroid) {

    item?.let {
        setImageResource(when(item.isPotentiallyHazardous) {
            true -> R.drawable.ic_status_potentially_hazardous
            else -> R.drawable.ic_status_normal
        })
    }
}


/**
 * Binding adapter for image of the day
 */
@BindingAdapter("imageOfTheDay")
fun ImageView.imageOfTheDay (item : PictureOfDay) {
    item?.let {
        when(item.mediaType) {
            Constants.MEDIA_TYPE -> Picasso.with(context).load(item.url)
                .placeholder(R.drawable.placeholder_picture_of_day)
                .error(R.drawable.ic_broken_image)
                .into(this)

            else -> Picasso.with(context).load(R.drawable.asteroid_safe).into(this)

        }


    }
}


/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}
