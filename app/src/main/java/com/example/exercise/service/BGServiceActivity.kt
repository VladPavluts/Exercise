package com.example.exercise.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.work.*
import com.example.exercise.R
import kotlinx.android.synthetic.main.activity_bgservice.*

class BGServiceActivity : AppCompatActivity() {

    companion object{
        const val KEY="KEY"
        const val KEY_BG="KEY_BG"
        const val PROGRESS_UPDATE_ACTION = "PROGRESS_UPDATE_ACTION"
        const val PROGRESS_VALUE_KEY = "PROGRESS_VALUE_KEY"
        const val SERVICE_STATUS = "SERVICE_STATUS"
    }
    lateinit var textCom: TextView
    var backgroundProgressReceiver: BackgroundProgressReceiver ?= null
    private val workManager = WorkManager.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bgservice)
        val text2= findViewById<TextView>(R.id.textView2)
        textCom= findViewById(R.id.progressTV)
        text2.text="The progress"
        textCom.text="is 0 %"
        btn_service.setOnClickListener{
            stopAll()
            startService(Intent(this, ServiceProgress::class.java))
        }
        btn_intent_service.setOnClickListener {
            stopAll()
            startService(Intent(this, IntentServiceProgress::class.java))
        }

        btn_work_manager.setOnClickListener{
            stopAll()

            workManager.enqueue(
                OneTimeWorkRequest.Builder(ProgressWork::class.java)
                    .build())
        }

    }
    private fun stopAll(){
        stopService(Intent(this, IntentServiceProgress::class.java))
        stopService(Intent(this, ServiceProgress::class.java))

    }
    fun subscribeForProgressUpdates() {
        if (backgroundProgressReceiver == null) {
            backgroundProgressReceiver = BackgroundProgressReceiver()
        }
        val progressUpdateActionFilter = IntentFilter()
        progressUpdateActionFilter.addAction(PROGRESS_UPDATE_ACTION)
        registerReceiver(backgroundProgressReceiver, progressUpdateActionFilter)
    }
    override fun onResume() {
        subscribeForProgressUpdates()
        super.onResume()
    }
    override fun onPause() {
        if(backgroundProgressReceiver != null) {
            unregisterReceiver(backgroundProgressReceiver)
        }
        super.onPause()
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(backgroundProgressReceiver)
    }


    inner class BackgroundProgressReceiver : BroadcastReceiver() {

        override  fun onReceive(context: Context, intent: Intent) {
            val progress = intent.getIntExtra(PROGRESS_VALUE_KEY, -1)
            val status = intent.getIntExtra(SERVICE_STATUS, -1)
            if(progress>-1){
                textCom.text=progress.toString()
            }else {
                if (status == 0) {
                    textCom.text = "STARTE"
                } else {
                    if (status == 1) {
                        textCom.text = "DONE!"
                    }
                }
            }


        }
    }

}
