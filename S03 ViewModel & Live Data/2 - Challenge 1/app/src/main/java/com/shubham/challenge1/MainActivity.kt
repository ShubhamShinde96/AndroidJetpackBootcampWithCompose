package com.shubham.challenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
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
        viewModel =ViewModelProvider(this, viewModelFactory).get(ActivityMainViewModel::class.java)

        binding.apply {

            additionTextView.text = viewModel.getCountValue().toString()

            addButton.setOnClickListener {

                val aa = additionTextView.text.toString()

                if(aa.isNotEmpty()) {

                    additionTextView.text = viewModel.updateCountValue(valueEdtx.text.toString().toInt()).toString()
                }
            }

        }

    }
}