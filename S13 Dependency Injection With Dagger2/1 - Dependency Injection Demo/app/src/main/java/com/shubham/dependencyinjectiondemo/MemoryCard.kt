package com.shubham.dependencyinjectiondemo

import android.util.Log
import javax.inject.Inject

//class MemoryCard @Inject constructor() {
class MemoryCard {

    // Since we don't own this class we should not be able to add this @Inject annotation here., so lets remove it.
    // So now we're creating module class for this dependency.

    init {
        Log.i("MYTAG","Memory Card Constructed")
    }

    fun getSpaceAvailablity(){
        Log.i("MYTAG","Memory space available")
    }
}