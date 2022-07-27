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
import com.shubham.navigationchallenge1.databinding.FragmentNameBinding

class NameFragment : Fragment() {

    private lateinit var binding: FragmentNameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_name, container, false)

        binding.apply {

            nextButton.setOnClickListener {

                val nameStr = nameEditText.text.toString()

                if(!nameStr.isEmpty()) {

                    val bundle: Bundle = bundleOf("name_input" to nameStr)
                    it.findNavController().navigate(R.id.action_nameFragment_to_emailFragment, bundle)

                } else {

                    Toast.makeText(activity, "Please enter your name!", Toast.LENGTH_SHORT).show()
                }

            }
        }

        return binding.root
    }


}











































