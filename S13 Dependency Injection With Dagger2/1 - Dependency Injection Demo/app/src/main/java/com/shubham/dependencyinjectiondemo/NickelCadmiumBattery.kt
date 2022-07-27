package com.shubham.dependencyinjectiondemo

import android.util.Log
import javax.inject.Inject


// We should annotate the constructor of this class with @Inject annotation.
class NickelCadmiumBattery @Inject constructor() : Battery {

    override fun getPower() {
        Log.i("MYTAG", "Power from NickelCadmiumBattery")
    }


    // Now we have to create a module to provide this NickelCadmiumBattery as a battery dependency.

}