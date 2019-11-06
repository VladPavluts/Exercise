package com.example.exercise.api.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesDto(
    val results: List<MovieDto>
)