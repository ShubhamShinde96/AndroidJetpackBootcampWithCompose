package com.shubham.servieswithviewbinding

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService: Service() {

    init {
        Log.i(TAG, "Service has been created.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    // this will get called when client starts the service invoking onStart()

        Log.i(TAG, "Service Started.")

        // Receiving the passed data to this service.
        val name = intent?.getStringExtra(NAME)
        val marks = intent?.getIntExtra(MARKS, 0)

        Log.i(TAG, "name is $name, marks are $marks")

        return START_STICKY   // So there are lot of such return options, sometimes android system may have to destroy
        // and restart the running service in order to manage its resources among the most prioritised apps, this
        // return value will be used by android system to decide how the system restarts the service after being
        // destroyed

//        START_REDELIVER_INTENT : system will pass the last intent to the service when restarting
//        START_STICKY:  system will not pass the last intent when restarting
//        START_NOT_STICKY: system will not restart the service.

        //        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {

        Log.i(TAG, "Service has been destroyed.")
        super.onDestroy()
    }

    companion object {
        const val TAG = "MYTAG"
        const val NAME = "NAME"
        const val MARKS = "TOTAL MARKS"
    }

}













