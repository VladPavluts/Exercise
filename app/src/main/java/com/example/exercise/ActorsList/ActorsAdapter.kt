package com.example.exercise.ActorsList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise.ActorsList.EmptyActivity.Companion.createIntent
import com.example.exercise.R

class ActorsAdapter(val context: Context, var actors: List<Actor>,val callback: (Actor) -> Unit) :ListAdapter<Actor,ActorsAdapter.ViewHolder>(DiffCallback()){

    private  val inflater: LayoutInflater= LayoutInflater.from(context)

    override fun getItemCount(): Int =actors.size

    override fun getItem(position: Int)=actors[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),context,callback)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.actor_item,parent,false))
    }




    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val photo: ImageView =itemView.findViewById(R.id.actorPhoto)
        private val name: TextView =itemView.findViewById(R.id.actorName)



        fun bind(actor: Actor,context: Context,callback: (Actor) -> Unit){
            photo.setImageResource(actor.photo)
            name.text=actor.name
            val intent=createIntent(context,actor)
            itemView.setOnClickListener{
                callback.invoke(actor)
                context.startActivity(intent)
            }

        }
    }
}
private class DiffCallback : DiffUtil.ItemCallback<Actor>() {
    override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem == newItem
    }
}

