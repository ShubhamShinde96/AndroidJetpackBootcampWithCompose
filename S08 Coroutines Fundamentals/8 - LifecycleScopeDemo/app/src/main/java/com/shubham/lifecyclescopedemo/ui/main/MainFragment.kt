package com.shubham.lifecyclescopedemo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.shubham.lifecyclescopedemo.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        lifecycleScope.launch(Dispatchers.IO) { // This is how you can specify which thread you want to run this coroutine on.
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