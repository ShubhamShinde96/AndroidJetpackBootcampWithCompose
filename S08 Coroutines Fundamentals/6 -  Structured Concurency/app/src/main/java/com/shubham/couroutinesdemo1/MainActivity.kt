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

                CoroutineScope(Dispatchers.Main).launch {

//                    tvUserMessage.text = UserDataManager1().getTotalUserCount().toString()
                    tvUserMessage.text = UserDataManager2().getTotalUserCount().toString()
                }

            }
        }

        // All the problems arose during the previous lesson of unstructured concurrency can be solved with this
        // CoroutineScope function, notice that this time we are talking about an suspending function "coroutineScope"
        // "c" in this CoroutineScope is in small letter.

        // "coroutineScope" -> suspending function
        // "CoroutineScope" -> interface

        // "coroutineScope" guarantees the completion of the task when the suspending function returns.



    }

    private fun downloadUserData() {
        for (i in 1..900000) {
            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
        }
    }

}
































