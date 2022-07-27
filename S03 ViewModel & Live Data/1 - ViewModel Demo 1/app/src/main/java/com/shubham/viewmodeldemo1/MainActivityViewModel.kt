package com.shubham.viewmodeldemo1

import androidx.lifecycle.ViewModel // This is ViewModel not AndroidViewModel
// If you use AndroidViewModel then you have to pass instance of application as a constructor parameter.
// we will see examples of AndroidViewModel in upcoming sessions.
class MainActivityViewModel : ViewModel() {

    private var count = 0

    fun getCurrentCount() : Int = count

    fun getUpdatedCount() : Int {
        return ++count
    }


}