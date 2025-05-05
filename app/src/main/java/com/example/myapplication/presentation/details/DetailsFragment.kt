package com.example.myapplication.presentation.details

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.data.models.RemoteMovieDetailsModel
import com.example.myapplication.databinding.FragmentDetailsBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null // Binding for the details fragment
    private val binding get() = _binding!! // Non-null assertion for the binding

    private val detailsViewModel: DetailsViewModel by viewModels() // ViewModel for the details fragment

    // Get the arguments passed to this fragment
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false) // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the movie ID from safe args
        val movieId = args.id

        // Request movie details
        detailsViewModel.getMovieDetails(movieId)

        // Observe the movie details
        observeViewModel()
    }

    private fun observeViewModel() {
        detailsViewModel.movieDetailsLiveData.observe(viewLifecycleOwner) { movieDetails -> // Observe the movie details
            if (movieDetails != null) { // Check if movie details are not null
                updateUI(movieDetails) // Update the UI with the movie details
            }
        }



        detailsViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) { // Check if there is an error message
                // Show error to user
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(movie: RemoteMovieDetailsModel) {
        // Set title
        binding.tvMovieTitle.text = movie.title

        // Set poster image
        movie.posterPath?.let { posterPath ->
            val imageUrl = "https://image.tmdb.org/t/p/w500$posterPath"
            Glide.with(this)
                .load(imageUrl)
                .into(binding.ivMoviePoster) // Load the image using Glide
        }

        // Set release date
        binding.tvReleaseDate.text = "Release: ${formatDate(movie.releaseDate)}"

        // Set rating
        binding.tvRating.text = "★ ${movie.voteAverage?.toString() ?: "N/A"}/10"

        // Set overview
        binding.tvOverview.text = movie.overview ?: "No description available"

        // Set genres
        binding.chipGroupGenres.removeAllViews()
        movie.genres?.forEach { genre ->
            genre?.name?.let { genreName ->
                val chip = Chip(binding.chipGroupGenres.context)
                chip.text = genreName
                binding.chipGroupGenres.addView(chip)
            }
        }
    }

    private fun formatDate(dateString: String?): String {
        if (dateString.isNullOrEmpty()) return "Unknown"
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()) // Parse the date string
            val outputFormat = SimpleDateFormat("MMM d, yyyy", java.util.Locale.getDefault()) // Format the date
            val date = inputFormat.parse(dateString) // Convert the date string to a Date object
            outputFormat.format(date!!) // Format the date to the desired format
        } catch (e: Exception) {
            dateString // If parsing fails, return the original string
        }
    }
    override fun onDestroy() { // Clean up the binding to avoid memory leaks
        super.onDestroy()
        _binding = null
    }
}