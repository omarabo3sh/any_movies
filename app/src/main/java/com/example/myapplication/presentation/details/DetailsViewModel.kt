package com.example.myapplication.presentation.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Repository
import com.example.myapplication.data.models.RemoteMovieDetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _movieDetailsLiveData = MutableLiveData<RemoteMovieDetailsModel?>() // LiveData for movie details
    val movieDetailsLiveData get() = _movieDetailsLiveData // Expose LiveData to observe movie details

    private val _loading = MutableLiveData<Boolean>() // LiveData for loading state
    val loading get() = _loading // Expose LiveData to observe loading state

    private val _error = MutableLiveData<String?>()   // LiveData for error messages
    val error get() = _error // Expose LiveData to observe error messages

    fun getMovieDetails(movieId: Int) { // Function to fetch movie details
        _loading.value = true // Set loading state to true
        _error.value = null      // Clear any previous error messages

        viewModelScope.launch(IO) { // Launch a coroutine in the IO dispatcher
            try {
//                val data = repository.getMovieDetails(movieId) // Fetch movie details from the repository
//                _movieDetailsLiveData.postValue(data) // Post the fetched data to LiveData
            } catch (e: Exception) {
                Log.e("DetailsViewModel", "Error fetching movie details", e) // Log the error
                _error.postValue("Failed to load movie details: ${e.message}") // Post error message to LiveData
                _movieDetailsLiveData.postValue(null) // Post null to LiveData in case of error
            } finally {
                _loading.postValue(false) // Set loading state to false
            }
        }
    }
}