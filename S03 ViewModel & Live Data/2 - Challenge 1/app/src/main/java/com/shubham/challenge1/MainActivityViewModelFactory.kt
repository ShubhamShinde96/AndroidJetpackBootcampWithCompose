package com.shubham.challenge1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MainActivityViewModelFactory(private val startingTotalValue: Int): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ActivityMainViewModel::class.java)) {
            return ActivityMainViewModel(startingTotalValue) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }

}