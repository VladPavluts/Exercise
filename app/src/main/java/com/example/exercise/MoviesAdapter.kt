package com.example.exercise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(context: Context,var movies: List<Movie>,private val clickListener: (position: Int) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private  val inflater: LayoutInflater= LayoutInflater.from(context)

    override fun getItemCount(): Int =movies.size

    fun getItem(position: Int)=movies[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_movie,parent,false),clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class ViewHolder(itemView: View,listener: (position: Int) -> Unit): RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView =itemView.findViewById(R.id.image_id)
        private val title: TextView =itemView.findViewById(R.id.title)
        private val overview: TextView =itemView.findViewById(R.id.overview)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener(position)
                }
            }
        }

        fun bind(movie: Movie){
            poster.setImageResource(movie.posterRes)
            title.text=movie.title
            overview.text=movie.overview
        }
    }
}
