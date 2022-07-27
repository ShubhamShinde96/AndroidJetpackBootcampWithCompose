package com.shubham.navigationchallenge1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.shubham.navigationchallenge1.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        val nameStr = requireArguments().getString("name_input")
        val emailStr = requireArguments().getString("email_input")

//        Toast.makeText(activity, "Name: $nameStr, Email: $emailStr", Toast.LENGTH_LONG).show()

        binding.apply {

            nameTextView.text = nameStr
            emailTextView.text = emailStr

            termsButton.setOnClickListener {

                it.findNavController().navigate(R.id.action_welcomeFragment_to_termsFragment)

            }

        }



        return binding.root
    }


}