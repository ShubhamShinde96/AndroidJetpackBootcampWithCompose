package com.shubham.servieswithviewbinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import com.shubham.servieswithviewbinding.MyBackgroundService.Companion.MARKS
//import com.shubham.servieswithviewbinding.MyBackgroundService.Companion.NAME
//import com.shubham.servieswithviewbinding.MyBackgroundService.Companion.TAG
import com.shubham.servieswithviewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceIntent = Intent(this, MyBackgroundService::class.java)

        // This is how you can send the data to the service.
//        serviceIntent.putExtra(NAME, "Alex") // you can use companion objects like this by importing the class
        serviceIntent.putExtra(MyBackgroundService.NAME, "Alex")  //you can use companion object like this by stating the reference to the class
        serviceIntent.putExtra(MyBackgroundService.MARKS, 70)

        binding.btnStart.setOnClickListener {
            Log.i(MyBackgroundService.TAG, "Starting service.")
            startService(serviceIntent)
        }

        binding.btnStop.setOnClickListener {
            Log.i(MyBackgroundService.TAG, "Stopping service.")
            stopService(serviceIntent)
        }

        // We need to add the service to the manifest file.
    }


}