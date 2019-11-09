package com.example.exercise.main

import androidx.lifecycle.*
import com.example.exercise.Mapper.MoviesRepo
import com.example.exercise.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class MoviesViewModel(val moviesRepo: MoviesRepo) : ViewModel() {
    val moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    private val isProgressBarVisibleMutableLiveData = MutableLiveData<Boolean>()
    val movies:LiveData<List<Movie>> = moviesLiveData
    val isProgressBarVisible: LiveData<Boolean> = isProgressBarVisibleMutableLiveData

    fun launching(count:Int) {
        viewModelScope.launch{
            try {
                isProgressBarVisibleMutableLiveData.value = true
                val movies = withContext(Dispatchers.Default) { moviesRepo.getMovies(count) }
                moviesLiveData.value = movies
            }
            catch (error: Throwable){
            }
            finally {
                isProgressBarVisibleMutableLiveData.value = false
            }
        }
    }
}
class MoviesViewModelFactory(
    private val moviesRepository: MoviesRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == MoviesViewModel::class.java) {
            @Suppress("UNCHECKED_CAST")
            MoviesViewModel(
                moviesRepository
            ) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}