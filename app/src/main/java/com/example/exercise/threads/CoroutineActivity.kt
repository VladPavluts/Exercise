package com.example.exercise.threads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise.R

class CoroutineActivity : AppCompatActivity() {


    companion object{
        const val KEY="KEY"
        const val KEY_COROUTINE="KEY_COROUTINE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)


        val text=intent.getStringExtra(KEY)

        val counterFragment=CounterFragment.newInstance(text)

        supportFragmentManager.beginTransaction()
            .add(R.id.activity_task_fragment,counterFragment)
            .commit()
    }
}
