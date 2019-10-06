package com.example.exercise.ActorsList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise.DetailsActivity
import com.example.exercise.Movie
import com.example.exercise.R

class EmptyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
    }
    companion object{
        private const val ARGS_ACTOR="ARGS_ACTOR"
        fun createIntent(context: Context, actor: Actor): Intent {
            val intent = Intent(context, EmptyActivity::class.java)
            intent.putExtra(ARGS_ACTOR, actor)
            return intent
        }
    }
}
