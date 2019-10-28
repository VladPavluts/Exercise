package com.example.exercise.threads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.exercise.R
import com.example.exercise.ViewModel.CoroutineViewModel
import kotlinx.android.synthetic.main.counterfragment.*

class CounterFragment :Fragment(){
    private  lateinit var viewModel: CoroutineViewModel
    private lateinit var taskCreateButton: Button
    private lateinit var taskStartButton: Button
    private lateinit var taskCancelButton: Button
    private var task: TaskEventListener.Task? = null
    private  lateinit var contentText:TextView
    companion object {
        private const val KEYS="KEYS"
        fun newInstance(task_type:String): CounterFragment {
            val f = CounterFragment()
            val args = Bundle()
            args.putString(KEYS,task_type)
            f.arguments = args

            return f
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.counterfragment,container,false)
      /*  if(arguments?.getString(KEYS)==CoroutineActivity.KEY_COROUTINE)
            task= CoroutineTask(this)
        if(arguments?.getString(KEYS)==ThreadsActivity.KEY_THREAD)
            task= ThreadTask(this)*/

        viewModel= ViewModelProviders.of(this).get(CoroutineViewModel::class.java)

        taskCreateButton = view.findViewById(R.id.button)
        taskStartButton = view.findViewById(R.id.button2)
        taskCancelButton = view.findViewById(R.id.button3)

        contentText = view.findViewById(R.id.textView)

        taskCreateButton.setOnClickListener { viewModel.onCreateTask() }
        taskStartButton.setOnClickListener { viewModel.onStartTask() }
        taskCancelButton.setOnClickListener { viewModel.onCancelTask() }
        contentText.text=arguments?.getString(KEYS) ?: throw  IllegalArgumentException()

        viewModel.contentsText.observe(this, Observer { string ->
            textView.text = string
        })

        return view
    }

    /*override fun onPostExecute() {
        contentText.text=getString(R.string.done)
    }

    override fun onPreExecute() {
        contentText.text="Created"
    }

    override fun onProgressUpdate(progress: Int) {
        contentText.text="$progress..."
    }*/
}