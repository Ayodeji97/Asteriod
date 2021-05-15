package com.udacity.asteroidradar.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var asteroidAdapter: AsteroidAdapter

  //  private val asteroidList = generateDummyList(100)

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        /**
         * Adapter TODO : check later for click
         * */
        asteroidAdapter = AsteroidAdapter(AsteroidAdapter.AsteroidClickListener {asteroidId ->
            Toast.makeText(requireContext(), "Asteroid $asteroidId", Toast.LENGTH_SHORT).show()
        })

        binding.asteroidRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.asteroidRecycler.adapter = asteroidAdapter

        // dummy data
        //asteroidAdapter.submitList(asteroidList)

        viewModel.asteroid.observe(viewLifecycleOwner, Observer {
            it?.let {
                asteroidAdapter.submitList(it)
            }
        })


        // image
        viewModel.imageOfTheDay.observe(viewLifecycleOwner, Observer {pictureOfDay ->
            Picasso.with(requireContext()).apply {
                Log.i("MAINFRAGMENT", "${pictureOfDay.url}")
                load(pictureOfDay.url).into(binding.activityMainImageOfTheDay)
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
