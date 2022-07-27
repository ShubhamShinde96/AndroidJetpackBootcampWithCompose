package com.shubham.servicesbroadcastreceiver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*

class StopWatchService: Service() {

    private val timer = Timer()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        val time = intent.getDoubleExtra(CURRENT_TIME, 0.0)
        timer.scheduleAtFixedRate(StopWatchTimerTask(time), 0, 1000)

        Log.i("MYTAG", "Inside onStartCommand()")

        return START_STICKY

//        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {

        timer.cancel()
        super.onDestroy()
    }

    companion object {
        const val CURRENT_TIME = "current time"
        const val UPDATED_TIME = "updated time"
    }

    //creating inner class, this will take current time as a constructor parameter.
    private inner class StopWatchTimerTask(private var time: Double) : TimerTask() {

        override fun run() {

            val intent = Intent(UPDATED_TIME)
            time++
            intent.putExtra(CURRENT_TIME, time)
            sendBroadcast(intent)
        }

    }


}