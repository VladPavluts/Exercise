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
import com.example.exercise.Movie
import com.example.exercise.R

class MyFragment: Fragment() {
    companion object {
        private val KEY="KEY"
        fun newInstance(movie: Movie): MyFragment {
            val f = MyFragment()
            val args = Bundle()
            args.putParcelable(KEY,movie)
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

        view.findViewById<ImageView>(R.id.IVendgame_b).setImageResource(movie?.backdropRes?: 0)
        view.findViewById<ImageView>(R.id.IVendgame_fon).setImageResource(movie?.posterRes?: 0)
        view.findViewById<TextView>(R.id.avenID).text = movie?.title
        view.findViewById<TextView>(R.id.IDrele).text = movie?.releaseDate
        view.findViewById<TextView>(R.id.overviewID).text = movie?.overview

        val movieButton: Button = view.findViewById(R.id.movieButtonId)
        movieButton.setOnClickListener {
            onTrailerMovie(movie?.trailerUrl)
        }
        return view
    }


    private fun onTrailerMovie(url:String?){
        val intent= Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}