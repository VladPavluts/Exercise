package com.example.exercise.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise.Mapper.Dependencies.Dependency
import com.example.exercise.R
import com.example.exercise.details.DetailsActivity
import com.example.exercise.service.BGServiceActivity
import com.example.exercise.threads.CoroutineActivity
import com.example.exercise.threads.ThreadsActivity

class MainActivity : AppCompatActivity() {

    var counter=1

    private lateinit var viewModel: MoviesViewModel
    private  lateinit var adapter: MoviesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list=findViewById<RecyclerView>(R.id.moviesList)

        viewModel = ViewModelProviders.of(this,
            MoviesViewModelFactory(Dependency.moviesRepo)
        ).get(MoviesViewModel::class.java)

        viewModel.launching(counter)

        findViewById<Button>(R.id.ButtonForShowMore).setOnClickListener {
            counter++
            viewModel.launching(counter)

        }

        viewModel.movies.observe(this, Observer { movies ->
            adapter.movies = movies
            adapter.notifyDataSetChanged()
        })
        val progressBar = findViewById<ProgressBar>(R.id.moviesProgressBar)
        viewModel.isProgressBarVisible.observe(this, Observer { isVisible ->
            progressBar.isVisible = isVisible
        })
        adapter = MoviesAdapter(this, emptyList()) { movies, position ->
            val intent = DetailsActivity.createIntent(this, position, movies)
            startActivity(intent)
        }

        list.adapter=adapter
        list.layoutManager = LinearLayoutManager(this)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.coroutine1, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean{
        if(item.itemId == R.id.coroutine11){
            val intent=Intent(this,CoroutineActivity::class.java)
            intent.putExtra(CoroutineActivity.KEY,CoroutineActivity.KEY_COROUTINE)
            startActivity(intent)
            return true
        }
        if(item.itemId == R.id.thread22){
            val intent=Intent(this,ThreadsActivity::class.java)
            intent.putExtra(ThreadsActivity.KEY,ThreadsActivity.KEY_THREAD)
            startActivity(intent)
            return true
        }
        if(item.itemId == R.id.BGActivity){
            val intent=Intent(this,BGServiceActivity::class.java)
            intent.putExtra(BGServiceActivity.KEY,BGServiceActivity.KEY_BG)
            startActivity(intent)
            return true
        }
        return true

    }

}
