package com.example.exercise.threads

import android.os.Handler
import android.os.Looper
import android.os.SystemClock

class ThreadTask(private val listener: TaskEventListener.Lifecycle):TaskEventListener.Task {
    var thread:Thread?=null

    override fun createTask() {
        thread = object : Thread() {
            override fun run() {
                repeat (10) {

                    if (!interrupted()) {
                        runOnUiThread(Runnable {
                            listener.onProgressUpdate(it)
                        })

                        SystemClock.sleep(500)
                    } else {
                        return
                    }
                }

                runOnUiThread(Runnable { listener.onPostExecute() })
            }
        }

        listener.onPreExecute()
    }




    override fun startTask(): Boolean? {
        thread?.start()

        return true
    }

    override fun cancelTask() {
        thread?.interrupt()
    }
    private fun runOnUiThread(runnable: Runnable) {
        Handler(Looper.getMainLooper()).post(runnable)
    }
}