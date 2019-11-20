package com.example.exercise.service

import android.app.Service
import android.content.Intent
import android.os.*

class ServiceProgress : Service() {

    var isDestroyed = false
    lateinit var mServiceLooper: Looper
    lateinit var mServiceHandler: ServiceHandler


    inner class ServiceHandler(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            for (i in 0..100 ) {
                if (!isDestroyed)  {
                    SystemClock.sleep(100)
                    val intent = Intent(BGServiceActivity.PROGRESS_UPDATE_ACTION)
                    intent.putExtra(BGServiceActivity.PROGRESS_VALUE_KEY, i)
                    sendBroadcast(intent)
                }
            }
            val broadcastIntent = Intent(BGServiceActivity.PROGRESS_UPDATE_ACTION)
            broadcastIntent.putExtra(BGServiceActivity.SERVICE_STATUS, 1)
            sendBroadcast(broadcastIntent)
            stopSelf(msg.arg1)

        }
    }

    override fun onCreate() {

        val thread = HandlerThread("ServiceStartArguments")
        thread.start()

        mServiceLooper = thread.looper
        mServiceHandler = ServiceHandler(mServiceLooper)

        val broadcastIntent = Intent(BGServiceActivity.PROGRESS_UPDATE_ACTION)
        broadcastIntent.putExtra(BGServiceActivity.SERVICE_STATUS, 0)
        sendBroadcast(broadcastIntent)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val msg = mServiceHandler.obtainMessage()
        msg.arg1 = startId
        mServiceHandler.sendMessage(msg)

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onDestroy() {
        isDestroyed = true
        super.onDestroy()
    }

}