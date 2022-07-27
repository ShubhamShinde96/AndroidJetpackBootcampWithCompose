package com.shubham.navdemo1

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.shubham.navdemo1.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        rootView =  inflater.inflate(R.layout.fragment_home, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.button.setOnClickListener {

            if(!binding.editTextTextPersonName.text.toString().isEmpty()) {

                val bundle: Bundle =
                    bundleOf("user_input" to binding.editTextTextPersonName.text.toString())
                it.findNavController().navigate(R.id.action_homeFragment_to_secondFragment, bundle)

                // But passing data to destination is not quite recommended, instead best way is to use ViewModel to
                // share data between destinations.

            } else {

                Toast.makeText(activity, "Please insert your name!", Toast.LENGTH_LONG).show()
            }


        }

        return binding.root
    }


}