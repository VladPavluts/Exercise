package com.example.exercise.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Movie(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "posterUrl")val posterUrl: String,
    @ColumnInfo(name = "backdropUrl")val backdropUrl: String,
    @ColumnInfo(name = "overview")val overview: String,
    @ColumnInfo(name = "releaseDate")val releaseDate: String,
    @ColumnInfo(name = "popularity")val popularity: Double
) : Parcelable