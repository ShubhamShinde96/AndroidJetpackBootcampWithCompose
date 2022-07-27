package com.shubham.a2_challenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import com.shubham.a2_challenge1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var button: Button

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        button = findViewById(R.id.control_button)
        binding.controlButton.setOnClickListener {
            startOrStopProgressBar()
        }

    }

    private fun startOrStopProgressBar() {

//        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        binding.apply {
            if (progressBar.visibility == View.GONE) {
                progressBar.visibility = View.VISIBLE
                controlButton.text = "Stop"
            } else {
                progressBar.visibility = View.GONE
                controlButton.text = "Start"
            }
        }
    }

}