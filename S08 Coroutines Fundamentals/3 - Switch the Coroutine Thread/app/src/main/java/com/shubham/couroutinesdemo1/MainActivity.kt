package com.shubham.couroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.shubham.couroutinesdemo1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        binding.apply {

            btnCount.setOnClickListener {
                tvCount.text = count++.toString()
            }
            btnDownloadUserData.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {

                    downloadUserData()
                }
            }
        }

        // In kotlin coroutines whenever a coroutine is suspended the current stack frame of the function is copied
        // and saved in the memory. When the function resumes after completing its task then the stack frame is copies back from
        // where it was saved and stars running again.

        // withContext, withTimeout, withTimeoutOrNull, join, delay, wait, SupervisorScope, CoroutineScope these are some
        // examples of suspending functions provided by the kotlin coroutine API.


    }

    // With this "suspend" modifier we label a function as a function with long running task.
    private suspend fun downloadUserData() {
        for (i in 1..900000) {
//            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
//            binding.tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            // We cannot do assign text like this as we are calling this function from the background thread
            // So now we're going to switch from one thread to another thread

            withContext(Dispatchers.Main){
                binding.tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }
            // withContext function is a suspending function & we cannot call it directly from normal function
            // so we have to add suspend modifier to this function declaration before fun keyword
        }
    }

}
































