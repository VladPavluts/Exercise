package com.example.exercise.threads.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise.threads.CoroutineTask
import com.example.exercise.threads.TaskEventListener

class CoroutineViewModel : ViewModel(){
    val contentsText:MutableLiveData<String> = MutableLiveData()
    private val listener=object :TaskEventListener.Lifecycle{
        override fun onPreExecute()= Unit
        override fun onPostExecute() {
            contentsText.value="Done!"
        }
        override fun onProgressUpdate(progress: Int) {
            contentsText.value=progress.toString()
        }
    }
    private val coroutineTask=CoroutineTask(listener)

    fun onCreateTask(){
        contentsText.value="Created!"
        coroutineTask.createTask()
    }
    fun onStartTask(){
        val started = coroutineTask.startTask()

        if (started == null || started == false) {
            contentsText.value ="Must create a task!!!"
        }
    }
    fun onCancelTask(){
        val canceled = coroutineTask.cancelTask()

        if (canceled == null) {
            contentsText.value = "Task is cancelled"
        }
    }


}