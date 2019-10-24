package com.example.exercise.threads

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class CoroutineTask(private val listener: TaskEventListener.Lifecycle): CoroutineScope,TaskEventListener.Task {
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob()
    private var job: Job?=null

    override fun createTask() {
        job=launch(context = Dispatchers.IO,start=CoroutineStart.LAZY){
            repeat (10) {
                launch (Dispatchers.Main) {
                    listener.onProgressUpdate(it)
                }

                delay(500L)
            }

            launch(Dispatchers.Main) {
                listener.onPostExecute()
            }
        }

        listener.onPreExecute()
    }


    override fun startTask(): Boolean? {
        return job?.start()
    }

    override fun cancelTask() {
        job?.cancel()
        coroutineContext.cancel()
    }



}