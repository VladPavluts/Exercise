package com.example.exercise.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.exercise.R
import com.example.exercise.details.MyFragment.Companion.newInstance
import com.example.exercise.data.Movie


class DetailsActivity : AppCompatActivity() {

    private lateinit var mPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        mPager = findViewById(R.id.viewpager)
        val position=intent.getIntExtra(KEYPOS,0)
        val movies=intent.getParcelableArrayListExtra<Movie>(KEY_MOVIES)
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager,movies)
        mPager.adapter = pagerAdapter
        mPager.currentItem=position
    }

    companion object{
        private const val KEYPOS="KEYPOS"
        private const val KEY_MOVIES="KEY_MOVIES"
        fun createIntent(context: Context,position: Int,movies: List<Movie>): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(KEYPOS,position)
            intent.putExtra(KEY_MOVIES,ArrayList(movies))
            return intent
        }
    }
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager,val movies: List<Movie>) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = movies.size
        override fun getItem(position: Int): Fragment = newInstance(movies[position],movies)
    }
}