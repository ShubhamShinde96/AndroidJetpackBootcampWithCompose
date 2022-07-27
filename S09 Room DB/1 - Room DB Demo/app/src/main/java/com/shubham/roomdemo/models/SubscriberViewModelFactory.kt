package com.shubham.roomdemo.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubham.roomdemo.repository.SubscriberRepository
import java.lang.IllegalArgumentException

class SubscriberViewModelFactory(private val subscriberRepository: SubscriberRepository): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        // Following code is just boilerplate, we everytime need to use for every ViewModelFactory

        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
            return SubscriberViewModel(subscriberRepository) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }

}