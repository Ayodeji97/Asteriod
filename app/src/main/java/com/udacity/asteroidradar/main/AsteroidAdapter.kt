package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidListItemBinding

class AsteroidAdapter (val clickListener : AsteroidClickListener) : ListAdapter<Asteroid, AsteroidAdapter.AsteroidViewHolder>(AsteroidDiffCallback()) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {

        holder.bind(getItem(position), clickListener)

    }

    class AsteroidViewHolder private constructor(val binding: AsteroidListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind (item : Asteroid, clickListener: AsteroidClickListener ) {

            binding.asteriod = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from (parent: ViewGroup) : AsteroidViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AsteroidListItemBinding.inflate(layoutInflater, parent, false)
                return AsteroidViewHolder(binding)
            }
        }

    }


    class AsteroidDiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid) = oldItem == newItem

    }

    // click listener
    class AsteroidClickListener(val clickListener : (asteroidId : Long) -> Unit) {
        fun onClick (asteroid: Asteroid) = clickListener(asteroid.id)
    }
}