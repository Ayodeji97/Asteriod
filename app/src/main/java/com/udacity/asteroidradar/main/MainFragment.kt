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
import com.udacity.asteroidradar.utils.AsteroidFilters


class MainFragment : Fragment() {

    private lateinit var asteroidAdapter: AsteroidAdapter


    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onViewCreated(), which we
     * do in this Fragment.
     */
    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(
            this,
            MainViewModel.Factory(activity.application)
        ).get(MainViewModel::class.java)
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

        viewModel.picOfDay.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                Log.i("NULLCHECK","Picture of day is null")
            }
            Picasso.with(requireContext()).load(it.url).into(binding.activityMainImageOfTheDay)
        })


        viewModel.asteroidList.observe(viewLifecycleOwner, Observer {
            it?.let {
                asteroidAdapter.submitList(it)
            }
        })


        binding.asteroidRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.asteroidRecycler.adapter = asteroidAdapter


        setHasOptionsMenu(true)

        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.onClickedFilter(
            when(item.itemId) {
                R.id.show_today_menu -> {
                    AsteroidFilters.SHOW_TODAY
                }
                R.id.show_week_menu -> {
                    AsteroidFilters.SHOW_WEEK
                }
                else -> {
                    AsteroidFilters.SHOW_SAVE
                }
            }
        )
        return true
    }
}
