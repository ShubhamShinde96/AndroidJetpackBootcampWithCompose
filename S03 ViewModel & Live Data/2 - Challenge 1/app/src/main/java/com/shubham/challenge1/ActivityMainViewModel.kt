package com.shubham.challenge1

import androidx.lifecycle.ViewModel

//class ActivityMainViewModel(var startingTotalValue: Int): ViewModel() {
class ActivityMainViewModel(startingTotalValue: Int): ViewModel() {

    // If we need to have custom view model, for ex if we want to pass starting value to view model as a constructor parameter
    // In this case we can use ViewModelFactory
    private var count = 0;

    init {
        count = startingTotalValue
    }

    fun getCountValue() = count

    fun updateCountValue(value: Int): Int {
        count += value
        return count
    }

}