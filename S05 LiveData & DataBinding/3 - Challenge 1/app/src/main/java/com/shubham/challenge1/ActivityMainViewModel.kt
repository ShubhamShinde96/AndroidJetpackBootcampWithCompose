package com.shubham.challenge1

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityMainViewModel(startingTotalValue: Int): ViewModel() {

    private var count = MutableLiveData<Int>()
    var inputVal = MutableLiveData<String>()

    val countData: LiveData<Int> get() = count

    init {
        count.value = startingTotalValue
        inputVal.value = ""
    }

    fun updateCountValue() {

        if(!inputVal.value.isNullOrEmpty()){
            count.value = (count.value)?.plus(inputVal.value.toString().toInt())
        }

    }

}