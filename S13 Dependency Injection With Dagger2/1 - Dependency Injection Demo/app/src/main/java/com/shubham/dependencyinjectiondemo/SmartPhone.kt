package com.shubham.dependencyinjectiondemo

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

// In kotlin primary constructor is invisible but we need a visible constructor to annotate it with inject annotation.
// We need to use the "constructor" keyword explicitly to make the construct visible
// and then we'll add the "inject" annotation

@Singleton
class SmartPhone @Inject constructor(val battery: Battery, val simCard: SIMCard, val memoryCard: MemoryCard) {

    // We have 3 object params so we have to inject their constructor as well(make constructor visible and annotate with @Inject),
    // otherwise dagger will not be able to create objects of those dependencies.

    init {
        Log.i("MYTAG", "SmartPhone Constructed")
        battery.getPower()
        simCard.getConnection()
        memoryCard.getSpaceAvailablity()
    }

    fun makeACallWithRecording() {
        Log.i("MYTAG", "Calling.....")
    }
}