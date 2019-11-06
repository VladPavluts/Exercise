package com.example.exercise.api

import com.example.exercise.api.dto.MoviesDto
import com.example.exercise.api.dto.VideosDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIServiceTmdb {
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("page") page:Int,
        @Query("api_key") apiKey: String = "ec1cb3d3da9774ef8b18b519b0a8d5c4"
    ): MoviesDto

    @GET("movie/{movieId}/videos")
    suspend fun getVideos(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = "ec1cb3d3da9774ef8b18b519b0a8d5c4"
    ): VideosDto
}