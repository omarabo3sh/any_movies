package com.example.myapplication.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.mapToPresentation
import com.example.myapplication.data.models.MovieResult
import com.example.myapplication.domain.GetMovieSortedUseCase
import com.example.myapplication.domain.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getMovieSortedUseCase: GetMovieSortedUseCase
) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<MovieResult>?>()
    val moviesLiveData get() = _moviesLiveData

    private val _loading = MutableLiveData<Boolean>()
    val loading get() = _loading

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch(IO) {
            _loading.postValue(true)
            try {
                getMovieUseCase.invoke().fold(
                    onSuccess = { movieModel ->
                        val sortedMovies = movieModel.results?.let {
                            getMovieSortedUseCase.getMovies(it)
                                .map { domainMovie -> mapToPresentation(domainMovie) }
                        }
                        _moviesLiveData.postValue(sortedMovies)
                    },
                    onFailure = {
                        _moviesLiveData.postValue(null)
                    }
                )
            } finally {
                _loading.postValue(false)
            }
        }
    }
}