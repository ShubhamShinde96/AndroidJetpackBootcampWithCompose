package com.shubham.lifecyclescopedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import com.shubham.lifecyclescopedemo.ui.main.MainFragment
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        progressBar = findViewById(R.id.progressBar)

        // Working with lifecycle scope is very straightforward, see below

//        lifecycleScope.launch { // This will launch on the main thread by default
        lifecycleScope.launch(IO) { // This is how you can specify which thread you want to run this coroutine on.

            /*delay(5000)
            progressBar.visibility = View.VISIBLE
            delay(10000)
            progressBar.visibility = View.GONE*/
            Log.i("MyTag", " Thread is: ${Thread.currentThread().name}") // Output: Thread is: DefaultDispatcher-worker-1
        }

        // Now because of this "lifecycleScope" all the active coroutines in the activity or fragment will
        // automatically terminate just before the activity or fragment ends or destroys

        // Sometimes we might need to suspend the execution of a code block, considering the current state of a
        // lifecycle object. for that we have three additional builders lifecycleScope, those are as follows.
        // Following launch methods basically get executed during that particular lifecycle event of activity or fragment

        lifecycleScope.launchWhenCreated {

        }

        lifecycleScope.launchWhenResumed {

        }

        lifecycleScope.launchWhenStarted {

        }


    }


}




























