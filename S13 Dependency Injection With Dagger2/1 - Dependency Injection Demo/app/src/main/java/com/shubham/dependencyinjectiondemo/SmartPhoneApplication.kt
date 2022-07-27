package com.shubham.dependencyinjectiondemo

import android.app.Application

class SmartPhoneApplication : Application() {

    lateinit var smartPhoneComponent: SmartPhoneComponent

    override fun onCreate() {
        super.onCreate()

        smartPhoneComponent = initDagger()
    }

    // creating a function to initialize dagger related components
    private fun initDagger() : SmartPhoneComponent  = DaggerSmartPhoneComponent.builder()
        .memoryCardModule(MemoryCardModule(100))
        .build()

    // Now we're going to assign the return value of this function to the smartPhoneComponent



}