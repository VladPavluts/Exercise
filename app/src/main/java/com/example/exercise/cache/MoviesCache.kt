package com.example.exercise.cache

import com.example.exercise.Database.MovieDao
import com.example.exercise.Database.VideoDao
import com.example.exercise.data.Movie
import com.example.exercise.data.Video

class MoviesCache(val movieDao: MovieDao,val videoDao: VideoDao){
    fun isCached(): Boolean{
        return movieDao.getAll().isNotEmpty()
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
    fun insertVideo(video: Video){
        videoDao.insert(video)
    }

}