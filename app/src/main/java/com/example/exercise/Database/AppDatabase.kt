package com.example.exercise.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.exercise.data.Movie
import com.example.exercise.data.Video

@Database(entities = [Movie::class, Video::class],version=2,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun videoDao(): VideoDao
}