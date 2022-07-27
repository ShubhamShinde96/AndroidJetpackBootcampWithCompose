package com.anushka.bindingdemo1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.anushka.bindingdemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main) // helps to render layout which we pass, but now we don't need this, as we are going to use DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        val button = findViewById<Button>(R.id.submit_button)

        binding.submitButton.setOnClickListener {
            displayGreeting()
        }

    }

    private fun displayGreeting() {
//        val messageView = findViewById<TextView>(R.id.greeting_text_view)
//        val nameText = findViewById<EditText>(R.id.name_edit_text)

        /*binding.apply {
            val text = "Hello! ${nameEditText.text}"
            greetingTextView.text = text
        }*/

        with(binding) {
            val text = "Hello! ${nameEditText.text}"
            greetingTextView.text = text
        }

//        val message = "Hello! "+ binding.nameEditText.text
//        binding.greetingTextView.text = message
    }

}
