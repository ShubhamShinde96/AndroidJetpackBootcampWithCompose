package com.shubham.navigationchallenge1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.shubham.navigationchallenge1.databinding.FragmentEmailBinding


class EmailFragment : Fragment() {

    private lateinit var binding: FragmentEmailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)

        val nameStr = requireArguments().getString("name_input")

        binding.apply {

            submitButton.setOnClickListener {

                val email = emailEditText.text.toString()

                if(!email.isEmpty()) {

                    val bundle: Bundle = bundleOf("name_input" to nameStr, "email_input" to email)

                    it.findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment, bundle)


                } else {

                    Toast.makeText(activity, "Please enter your email!", Toast.LENGTH_SHORT).show()
                }

            }

        }

//        Toast.makeText(activity, "Name: $nameStr", Toast.LENGTH_SHORT).show()

        return binding.root
    }


}