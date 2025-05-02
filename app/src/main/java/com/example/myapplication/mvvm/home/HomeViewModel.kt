package com.example.myapplication.mvvm.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MovieModel
import com.example.myapplication.data.MovieResult
import com.example.myapplication.mvvm.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: Repository) : ViewModel() { // ViewModel class for the HomeFragment


    val _moviesLiveData = MutableLiveData<List<MovieResult>?>() // LiveData to hold the list of movies
    val moviesLiveData get() = _moviesLiveData // Expose the LiveData to the fragment

    val _loading = MutableLiveData<Boolean>() // LiveData to hold the loading state
    val loading get() = _loading // Expose the LiveData to the fragment


    init {
        getMovies() // Call the function to get the movies when the ViewModel is created
    }

    private fun getMovies() { // Function to get the movies from the repository
        viewModelScope.launch(IO) { // Launch a coroutine in the IO context
            _loading.postValue(true) // Set the loading state to true
            val data = repository.getMovies() // Call the repository to get the movies
            _moviesLiveData.postValue(data) // Post the data to the LiveData
            _loading.postValue(false) // Set the loading state to false
        }
    }


}