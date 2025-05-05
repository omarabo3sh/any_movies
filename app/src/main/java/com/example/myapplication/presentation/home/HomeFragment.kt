package com.example.myapplication.presentation.home

import com.example.myapplication.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.models.MovieResult
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.presentation.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    var _binding: FragmentHomeBinding? = null // Nullable binding to avoid memory leaks
    val binding get() = _binding!! // Non-nullable binding to avoid null checks

    val homeViewModel: HomeViewModel by viewModels() // ViewModel instance using Hilt


    val movieAdapter by lazy { // Lazy initialization of the adapter
        MovieAdapter() // Adapter instance
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false) // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        movieAdapter.onItemClick = object : MovieAdapter.OnItemClick { // Set the click listener for the adapter
            override fun onClick(id: Int) {  // Handle item click
                findNavController() // Get the NavController for navigation
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id)) // Navigate to the details fragment

            }


        }
        observes() // Observe the LiveData from the ViewModel
    }


    private fun observes() {   homeViewModel.moviesLiveData.observe(viewLifecycleOwner) { movies ->
        binding.progressBar.visibility = View.VISIBLE
        if (!movies.isNullOrEmpty()) {
            movieAdapter.movieList = movies as? ArrayList<MovieResult>
            binding.recyclerMovie.adapter = movieAdapter
            binding.progressBar.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }



    // Observe the LiveData from the ViewModel
//            homeViewModel.moviesLiveData.observe(viewLifecycleOwner) {
//                movieAdapter.movieList = it as? ArrayList<MovieResult> // Update the adapter with the new data
//                binding.recyclerMovie.adapter = movieAdapter
//            }
//            homeViewModel.loading.observe(viewLifecycleOwner) {// Observe the loading state
//                if (it) {  // If loading is true, show the progress bar
//                    binding.progressBar.visibility = View.VISIBLE
//                } else { // If loading is false, hide the progress bar
//                    binding.progressBar.visibility = View.GONE
//                }
//
//            }



        }



    override fun onDestroyView() { // Clean up the binding to avoid memory leaks
        super.onDestroyView()
        _binding = null
    }
}