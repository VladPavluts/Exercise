package com.example.exercise.Mapper

import com.example.exercise.data.Movie
import com.example.exercise.data.Video
import com.example.exercise.api.APIServiceTmdb
import com.example.exercise.cache.MoviesCache


class MoviesRepo (val tmdbMapper: TmdbMapper,val apiServiceTmdb: APIServiceTmdb, val cache: MoviesCache){
    private var newPage=1
    suspend fun getMovies(count:Int):List<Movie>{

        if (cache.isCached() && count<=newPage) {
            return cache.getCachedMovies()
        }
            newPage=count
            val popularMoviesDTO=apiServiceTmdb.getMovies(count)
            val movies=tmdbMapper.map(popularMoviesDTO)
            cache.insertMovies(movies)
            return movies

    }
    suspend fun getMovieTrailerUrl(movie: Movie):String{
        val cachedUrl = cache.getMovieTrailerUrl(movie.id)
        if (cachedUrl != null) {
            return cachedUrl
        }
        val movieVideos=apiServiceTmdb.getVideos(movie.id)
        val url=tmdbMapper.mapTrailerUrl(movieVideos.results.first())
        cache.insertVideo(Video(movie.id, url))
        return url
    }




}
