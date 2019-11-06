package com.example.exercise.Mapper.Dependencies

import com.example.exercise.Mapper.MoviesRepo
import com.example.exercise.Mapper.TmdbMapper
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object Dependency {

    val moviesRepo by lazy { createMoviesRepo() }

    private val retrofit=Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    private fun createMoviesRepo(): MoviesRepo{
        return MoviesRepo(TmdbMapper(), retrofit.create())
    }
}