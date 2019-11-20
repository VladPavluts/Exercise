package com.example.exercise.service

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.work.Worker
import androidx.work.WorkerParameters

class ProgressWork(val appContext: Context, workerParams: WorkerParameters):
    Worker(appContext,workerParams){
    override fun doWork(): Result {
        return try{
            for(i in 0..100){
                SystemClock.sleep(100)
                val intent= Intent(BGServiceActivity.PROGRESS_UPDATE_ACTION)
                intent.putExtra(BGServiceActivity.PROGRESS_VALUE_KEY,i)
                appContext.sendBroadcast(intent)
            }
            val broadcastIntent=Intent(BGServiceActivity.PROGRESS_UPDATE_ACTION)
            broadcastIntent.putExtra(BGServiceActivity.SERVICE_STATUS,1)
            appContext.sendBroadcast(broadcastIntent)
            Result.success()
        } catch(error: Throwable){
            Result.failure()
        }
    }

}