package com.shubham.viewmodeldemo1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel // This is ViewModel not AndroidViewModel
// If you use AndroidViewModel then you have to pass instance of application as a constructor parameter.
// we will see examples of AndroidViewModel in upcoming sessions.
class MainActivityViewModel : ViewModel() {

    private var count = MutableLiveData<Int>()

    init {
        count.value = 0
    }

    val countData: LiveData<Int> get() = count

    fun updateCount() {
        count.value = (count.value)?.plus(1)
    }


}