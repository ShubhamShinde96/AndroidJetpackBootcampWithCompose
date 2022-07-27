package com.shubham.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shubham.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root )

        binding.submitButton.setOnClickListener {
            displayGreeting()
        }

    }

    private fun displayGreeting() {


        with(binding) {
            val text = "Hello! ${nameEditText.text}"
            greetingTextView.text = text
        }

    }

}