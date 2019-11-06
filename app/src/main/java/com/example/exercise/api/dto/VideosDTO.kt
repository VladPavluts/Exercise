package com.example.exercise.api.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideosDto(
    val results: List<VideoDto>
)