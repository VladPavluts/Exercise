package com.example.exercise.Mapper.Dependencies

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.exercise.Database.AppDatabase
import com.example.exercise.Mapper.MoviesRepo
import com.example.exercise.Mapper.TmdbMapper
import com.example.exercise.cache.MoviesCache
import com.example.exercise.cache.SharedPreferencesApi
import com.example.exercise.cache.SharedPreferencesImpl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object Dependency {

    val moviesRepo by lazy { createMoviesRepo() }
    class App:Application(){
        companion object{
            var appContext:Context?=null
        }

        override fun onCreate() {
            super.onCreate()
            appContext = this
        }
    }
    val db:AppDatabase by lazy {
        Room.databaseBuilder(App.appContext!!,AppDatabase::class.java,"Movie.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    private lateinit var sharedPreferencesApi:SharedPreferencesApi
    private val retrofit=Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    private fun createMoviesRepo(): MoviesRepo{
        return MoviesRepo(TmdbMapper(), retrofit.create(), MoviesCache(db.movieDao(),db.videoDao(),
            SharedPreferencesImpl(App.appContext!!)
        ) )
    }


}