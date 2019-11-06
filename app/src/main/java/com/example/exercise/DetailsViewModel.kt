package com.example.exercise

import androidx.lifecycle.*
import com.example.exercise.Mapper.MoviesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(val moviesRepo: MoviesRepo,val movie: Movie) : ViewModel() {
    val movieURLLiveData: MutableLiveData<String> = MutableLiveData()
    val movieURL: LiveData<String> = movieURLLiveData

    fun onButtonTrailer() {
        viewModelScope.launch {

            val movieURL = withContext(Dispatchers.IO) { moviesRepo.getMovieTrailerUrl(movie) }
            movieURLLiveData.value = movieURL
        }
    }

    class DetailsViewModelFactory(
        private val moviesRepository: MoviesRepo,
        private val movie: Movie?
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass == DetailsViewModel::class.java) {
                @Suppress("UNCHECKED_CAST")
                DetailsViewModel(
                    moviesRepository,
                    movie!!
                ) as T
            } else {
                throw IllegalArgumentException()
            }
        }
    }
}