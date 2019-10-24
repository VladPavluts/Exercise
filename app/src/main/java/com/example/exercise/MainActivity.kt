package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise.threads.CoroutineActivity
import com.example.exercise.threads.ThreadsActivity

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list=findViewById<RecyclerView>(R.id.moviesList)
        val movies=DataStorage.getMoviesList()

        val adapter = MoviesAdapter(this, movies) { position ->
            val intent = DetailsActivity.createIntent(this,position)
            startActivity(intent)
        }
        list.adapter=adapter
        list.layoutManager= LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.coroutine1, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean{
        if(item.itemId==R.id.coroutine11){
            val intent=Intent(this,CoroutineActivity::class.java)
            intent.putExtra(CoroutineActivity.KEY,CoroutineActivity.KEY_COROUTINE)
            startActivity(intent)
            return true
        }
        if(item.itemId==R.id.thread22){
            val intent=Intent(this,ThreadsActivity::class.java)
            intent.putExtra(ThreadsActivity.KEY,ThreadsActivity.KEY_THREAD)
            startActivity(intent)
            return true
        }
        return true


    }

}
