package com.shubham.challenge1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//class ActivityMainViewModel(var startingTotalValue: Int): ViewModel() {
class ActivityMainViewModel(startingTotalValue: Int): ViewModel() {

    // If we need to have custom view model, for ex if we want to pass starting value to view model as a constructor parameter
    // In this case we can use ViewModelFactory
    private var count = MutableLiveData<Int>()

    /*fun getCount() : MutableLiveData<Int> {
        return count
    }*/

    val countData: LiveData<Int> get() = count // This is how we can make our main live data object private
    // in order to maintain encapsulation.

    init {
        count.value = startingTotalValue
    }

//    fun getCountValue() = count // we don't need this getter anymore, as we are going to observe the live data.

    fun updateCountValue(value: Int) {
        count.value = (count.value)?.plus(value)
    }

}