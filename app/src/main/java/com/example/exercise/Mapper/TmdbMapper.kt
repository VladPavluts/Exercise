package com.example.exercise.Mapper

import com.example.exercise.Movie
import com.example.exercise.api.dto.MovieDto
import com.example.exercise.api.dto.MoviesDto
import com.example.exercise.api.dto.VideoDto

class TmdbMapper {
    companion object{
        private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
        private const val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780"
        private const val YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v="
    }
    fun map(moviesDto: MoviesDto): List<Movie> {
        return moviesDto.results.map { movieDto -> map(movieDto) }
    }

    private fun map(movieDto: MovieDto): Movie {
        return Movie(
            id = movieDto.id,
            title = movieDto.title,
            posterUrl = POSTER_BASE_URL + movieDto.posterPath,
            backdropUrl = BACKDROP_BASE_URL + movieDto.backdropPath,
            overview = movieDto.overview,
            releaseDate = movieDto.releaseDate
        )
    }

    fun mapTrailerUrl(videoDto: VideoDto): String {
        return YOUTUBE_BASE_URL + videoDto.key
    }
}