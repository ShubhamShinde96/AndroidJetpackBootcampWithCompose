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

                    tvUserMessage.text = UserDataManager1().getTotalUserCount().toString()
                }

            }
        }

        // There are situations where we need to launch more than one coroutines in suspending function & get some result
        // returned from functions.
        // There are two ways to do it: 1] Structured Concurrency 2] Unstructured Concurrency.

    }

    private fun downloadUserData() {
        for (i in 1..900000) {
            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
        }
    }

}
































