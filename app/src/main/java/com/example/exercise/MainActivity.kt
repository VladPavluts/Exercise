package com.example.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list=findViewById<RecyclerView>(R.id.moviesList)
        val movies=DataStorage.getMoviesList()

        val adapter = MoviesAdapter(this, movies) { position ->
            val movie = movies[position]
            val intent = DetailsActivity.createIntent(this, movie)
            startActivity(intent)
        }
        list.adapter=adapter
        list.layoutManager= LinearLayoutManager(this)
    }
}
