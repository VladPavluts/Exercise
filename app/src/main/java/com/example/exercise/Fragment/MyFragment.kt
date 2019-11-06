package com.example.exercise.Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.exercise.DetailsViewModel
import com.example.exercise.Mapper.Dependencies.Dependency
import com.example.exercise.Movie
import com.example.exercise.R

class MyFragment: Fragment() {
    companion object {
        private val KEY="KEY"
        private val KEY_MOVIES="KEY_MOVIES"
        fun newInstance(movie: Movie,movies: List<Movie>): MyFragment {
            val f = MyFragment()
            val args = Bundle()
            args.putParcelable(KEY,movie)
            args.putParcelableArrayList(KEY_MOVIES,ArrayList(movies))
            f.arguments = args

            return f
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.myfragment,container,false)

        val movie: Movie? = arguments?.getParcelable(KEY)
        val movies = arguments?.getParcelableArrayList<Movie>(KEY_MOVIES)
            ?: throw IllegalArgumentException("Missing movie argument")


        view.findViewById<ImageView>(R.id.IVendgame_b).load(movie?.backdropUrl)
        view.findViewById<ImageView>(R.id.IVendgame_fon).load(movie?.posterUrl)
        view.findViewById<TextView>(R.id.avenID).text = movie?.title
        view.findViewById<TextView>(R.id.IDrele).text = movie?.releaseDate
        view.findViewById<TextView>(R.id.overviewID).text = movie?.overview

        val viewModel=ViewModelProviders.of(this,
            DetailsViewModel.DetailsViewModelFactory(Dependency.moviesRepo, movie)
        ).get(DetailsViewModel::class.java)

        viewModel.movieURL.observe(
            this,
            Observer { trailerUrl -> onTrailerMovie(trailerUrl) }
        )
        val movieButton: Button = view.findViewById(R.id.movieButtonId)
        movieButton.setOnClickListener {
            viewModel.onButtonTrailer()
        }
        return view
    }


    private fun onTrailerMovie(url:String?){
        val intent= Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}