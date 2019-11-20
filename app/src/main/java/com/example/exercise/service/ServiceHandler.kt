package com.example.exercise.service

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.SystemClock


class ServiceHandler(looper: Looper) : Handler(looper) {
    override fun handleMessage(msg: Message) {
// Well calling mServiceHandler.sendMessage(message);  from onStartCommand this method will be called.
        for (i in 0..100 ) {
            val isDestroyed=false// Add your cpu-blocking activity here
            if (!isDestroyed)  {
                SystemClock.sleep(100)
                val intent = Intent(BGServiceActivity.PROGRESS_UPDATE_ACTION)
                intent.putExtra(BGServiceActivity.PROGRESS_VALUE_KEY, i)
                //sendBroadcast(intent)
            }
        }
        // the msg.arg1 is the startId used in the onStartCommand,so we can track the running service here.
       // stopSelf(msg.arg1)
    }
}
