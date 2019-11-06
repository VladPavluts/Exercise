package com.example.exercise.Mapper

import com.example.exercise.Movie
import com.example.exercise.api.APIServiceTmdb

class MoviesRepo (val tmdbMapper: TmdbMapper,val apiServiceTmdb: APIServiceTmdb){

    suspend fun getMovies(count:Int):List<Movie>{
        val popularMoviesDTO=apiServiceTmdb.getMovies(count)
        return tmdbMapper.map(popularMoviesDTO)

    }
    suspend fun getMovieTrailerUrl(movie: Movie):String{
        val movieVideos=apiServiceTmdb.getVideos(movie.id)
        return tmdbMapper.mapTrailerUrl(movieVideos.results.first())
    }

}