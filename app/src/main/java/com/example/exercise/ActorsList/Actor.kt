package com.example.exercise.ActorsList

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actor(val name: String,@DrawableRes val photo: Int): Parcelable