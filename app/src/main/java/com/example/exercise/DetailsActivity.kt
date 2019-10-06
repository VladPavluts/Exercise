package com.example.exercise

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val movie = intent?.getParcelableExtra<Movie>(ARGS_MOVIE)
            ?: throw IllegalArgumentException("Missing movie argument")
        val movieButton: Button = findViewById(R.id.movieButtonId)
        movieButton.setOnClickListener{onTrailerMovie(movie.trailerUrl)}

        val fonImage=findViewById<ImageView>(R.id.IVendgame_b)
        val poster=findViewById<ImageView>(R.id.IVendgame_fon)
        val releaseDate=findViewById<TextView>(R.id.IDrele)
        val name=findViewById<TextView>(R.id.avenID)
        val overview=findViewById<TextView>(R.id.overviewID)

        fonImage.setImageResource(movie.backdropRes)
        poster.setImageResource(movie.posterRes)
        releaseDate.text=movie.releaseDate
        name.text=movie.title
        overview.text=movie.overview
    }

    private fun onTrailerMovie(url:String){
        val intent= Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)

    }

    companion object{
        private const val ARGS_MOVIE="ARGS_MOVIE"
        fun createIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(ARGS_MOVIE, movie)
            return intent
        }
    }
}
