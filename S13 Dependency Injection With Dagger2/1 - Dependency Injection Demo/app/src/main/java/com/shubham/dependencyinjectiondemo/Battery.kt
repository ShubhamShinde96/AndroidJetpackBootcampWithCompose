package com.shubham.dependencyinjectiondemo

import android.util.Log
import javax.inject.Inject

interface Battery {

    // Making function abstract after converting the class to interface.
    fun getPower()

    /*init {
        Log.i("MYTAG","Battery Constructed")
    }*/

    /*fun getPower(){
        Log.i("MYTAG","Battery power is available")
    }*/

    // Now we can't pass battery as a parameter to SmartPhone class as we have changed it as a interface.
    // because we can't construct the Battery() now, as it is a interface from now on.
    // We need to create a class which implement the Battery interface and provide a dependency through a module.
}