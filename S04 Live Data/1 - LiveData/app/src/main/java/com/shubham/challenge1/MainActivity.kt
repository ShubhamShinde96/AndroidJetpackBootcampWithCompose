package com.shubham.challenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shubham.challenge1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ActivityMainViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModelFactory = MainActivityViewModelFactory(125)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ActivityMainViewModel::class.java)

        viewModel.countData.observe(this, Observer {
            binding.additionTextView.text = it.toString()
        })

        binding.apply {

            addButton.setOnClickListener {

                if(valueEdtx.text.isNotEmpty()) {

                    viewModel.updateCountValue(valueEdtx.text.toString().toInt()).toString()
                }
            }

        }

    }
}