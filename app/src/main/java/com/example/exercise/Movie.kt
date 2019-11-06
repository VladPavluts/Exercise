package com.example.exercise

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val backdropUrl: String,
    val overview: String,
    val releaseDate: String
) : Parcelable