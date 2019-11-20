package com.example.exercise.service

import android.app.IntentService
import android.content.Intent
import android.os.SystemClock

class IntentServiceProgress : IntentService("IntServ"){



    var isDestroyed = true

    override fun onHandleIntent(intent: Intent?) {
        isDestroyed = false
        var i = 0
        while(i <= 100 && !isDestroyed ) {
            SystemClock.sleep(100)
            val broadcastIntent = Intent(BGServiceActivity.PROGRESS_UPDATE_ACTION)
            broadcastIntent.putExtra(BGServiceActivity.PROGRESS_VALUE_KEY, i)
            sendBroadcast(broadcastIntent)
            ++i
        }
        val broadcastIntent = Intent(BGServiceActivity.PROGRESS_UPDATE_ACTION)
        broadcastIntent.putExtra(BGServiceActivity.SERVICE_STATUS, 1)
        sendBroadcast(broadcastIntent)
    }
    override fun onCreate() {
        val broadcastIntent = Intent(BGServiceActivity.PROGRESS_UPDATE_ACTION)
        broadcastIntent.putExtra(BGServiceActivity.SERVICE_STATUS, 0)
        sendBroadcast(broadcastIntent)
        super.onCreate()
    }
    override fun onDestroy() {
        isDestroyed = true
        super.onDestroy()
    }
}