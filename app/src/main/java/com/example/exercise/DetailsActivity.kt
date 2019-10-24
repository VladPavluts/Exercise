package com.example.exercise

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.exercise.DataStorage.getMoviesList
import com.example.exercise.Fragment.MyFragment.Companion.newInstance


class DetailsActivity : AppCompatActivity() {

    private lateinit var mPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        mPager = findViewById(R.id.viewpager)
        val position=intent.getIntExtra(KEYPOS,0)


        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter
        mPager.currentItem=position
    }

    companion object{
        private const val KEYPOS="KEYPOS"
        fun createIntent(context: Context,position: Int): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(KEYPOS,position)
            return intent
        }
    }
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = getMoviesList().size

        override fun getItem(position: Int): Fragment = newInstance(getMoviesList()[position])

    }
}