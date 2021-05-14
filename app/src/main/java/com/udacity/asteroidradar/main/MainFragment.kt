package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var asteroidAdapter: AsteroidAdapter

    private val asteroidList = generateDummyList(100)

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
        asteroidAdapter.submitList(asteroidList)

        setHasOptionsMenu(true)

        return binding.root
    }

    // dummy data for adapter
    private fun generateDummyList (size : Int) : ArrayList<Asteroid> {
        val list = ArrayList<Asteroid>()

        for (item in 0 until size) {
            val drawable = when (item % 3) {
                0 -> R.drawable.ic_status_potentially_hazardous
                1 -> R.drawable.ic_status_potentially_hazardous
                else ->R.drawable.ic_status_potentially_hazardous
            }

            val item = Asteroid(12, "SEE", "Sixth",
                4.5, 5.5, 6.6,
                7.5, true )

            list += item
        }

        return list
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
