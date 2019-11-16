package com.example.exercise.cache

import com.example.exercise.Database.MovieDao
import com.example.exercise.Database.VideoDao
import com.example.exercise.data.Movie
import com.example.exercise.data.Video
private const val EXPIRATION_TIME=600000.toLong()
class MoviesCache(val movieDao: MovieDao,val videoDao: VideoDao,private val preferenceHelper: SharedPreferencesApi){
    fun isCached(): Boolean{
        return movieDao.getAll().isNotEmpty()&&!isExpired()
    }
    fun getCachedMovies(): List<Movie> {
        return movieDao.getAll()
    }
    fun insertMovies(movies: List<Movie>){
        movieDao.insertAll(movies)
    }
    fun getMovieTrailerUrl(moviedId: Int): String? {
        val video = videoDao.getVideoByMovieId(moviedId)
        return video?.url
    }
    private fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()

        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }
    fun insertVideo(video: Video){
        videoDao.insert(video)
    }
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferenceHelper.lastCacheTime
    }

}