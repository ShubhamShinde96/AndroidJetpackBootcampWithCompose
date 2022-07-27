package com.shubham.viewmodelscopewithcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

//        viewModel.getUserData()

        viewModel.users.observe(this@MainActivity, Observer { myUsers ->

            myUsers.forEach {
                Log.i("MyTag", " Name is: ${it.name}")
            }

        })

    }
}