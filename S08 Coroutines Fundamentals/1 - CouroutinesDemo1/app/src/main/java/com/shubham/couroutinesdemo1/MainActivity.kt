package com.shubham.couroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.shubham.couroutinesdemo1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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


//                CoroutineScope(*) here which context you provide that coroutine will run on this passed context
                CoroutineScope(Dispatchers.IO).launch {
                    // "launch" is the coroutine builder, coroutine builders are extension functions which are used to launch main coroutine.
                    // "launch" returns an instance of a job, which can be used as a reference of coroutine, we can use this reference to kep track of the coroutine and to cancel the coroutine.
                    //  we use "launch builder" for coroutine which does not have any computed return value.
                    // async builder launched coroutines parallel without blocking the current thread.

                    // "CoroutineScope" is the interface we have used to provide the scope to our coroutine
                    // Like "CoroutineScope" we also have "GlobalScope" which operates on the whole lifetime
                    // of the application, although we very rarely uses this.

                    // Both of this scopes acts as a reference to the coroutine context, like we have used Dispatchers.IO as a context here.

                    // In kotlin android structure it is recommended to start coroutine using the main thread and then switch to the background thread.
                    // To launch coroutine to the main thread we use Dispatchers.Main -> Main(UI)
                    downloadUserData()

                    // If we use Dispatcher.IO then the coroutine will run on background thread from a shared pool on-demand.
                    // We use this IO dispatcher with local database, communicate with network and to work with files.
                    // Default dispatcher is used for CPU intensive tasks, such as sorting a list of data with 10000 list items,
                    // or parsing a huge JSON file with details of 100000 movies.
                    // Dispatcher.Uncomfined is a dispatcher used with GlobalScope, if we use this then coroutine will run on the current thread but if we suspend it and resume it then it will run on suspended thread
                }

            }

        }

        // in app we can launch many coroutines, we can have 100 coroutines running at the same time.


        // Structured concurrency: is a set of language features and best practices introduced for kotlin coroutines
        // to avoid leaks and to manage them productively.

    }

    private fun downloadUserData() {
        for (i in 1..900000) {
            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
        }
    }

}
































