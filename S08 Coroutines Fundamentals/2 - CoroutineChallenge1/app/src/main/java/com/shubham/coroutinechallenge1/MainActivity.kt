package com.shubham.coroutinechallenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.shubham.coroutinechallenge1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).apply {

            Log.d("MyTag", "Inside the thread ${Thread.currentThread().name}")
            // For some reason it is also printing this as a Main thread instead of background thread !
            // Need to find on this.

        }

        CoroutineScope(Dispatchers.Main).apply {

            Log.d("MyTag", "Inside the thread ${Thread.currentThread().name}")

        }

    }



}