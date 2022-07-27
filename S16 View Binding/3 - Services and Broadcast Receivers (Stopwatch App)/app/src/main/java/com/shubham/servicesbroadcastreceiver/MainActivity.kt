package com.shubham.servicesbroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shubham.servicesbroadcastreceiver.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var isStarted = false

    private lateinit var serviceIntent: Intent
    private var time  = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startOrStop()
        }

        binding.btnReset.setOnClickListener {
            reset()
        }

        serviceIntent = Intent(applicationContext, StopWatchService::class.java)



//        registerReceiver(updateTime, IntentFilter(StopWatchService.UPDATED_TIME))

        val intentFilter = IntentFilter()
        intentFilter.addAction(StopWatchService.UPDATED_TIME)
//        intentFilter.addAction("dssdf") // This way you can add more actions to Intent filter

        registerReceiver(updateTime, intentFilter)


        val intent = Intent("dhfdfh")
        intent.putExtra("fsfsfds", "dsisjijf")
        sendBroadcast(intent)
    }

    private fun startOrStop() {

        if (isStarted)
            stop()
        else
            start()

    }

    private fun start() {

        serviceIntent.putExtra(StopWatchService.CURRENT_TIME, time)
        startService(serviceIntent)

        binding.btnStart.text = "Stop"
        isStarted = true

    }

    private fun stop() {

        stopService(serviceIntent)

        binding.btnStart.text = "Start"
        isStarted = false
    }

    private fun reset() {

        stop()
        time = 0.0
        binding.tvTime.text = getFormattedTime(time)

    }

//    You can't create instance of an abstract class. You should remove abstract from the class declaration,
//    or you should create new class extending the abstract class and make instances of that class.

    // So basically here "object" keyword is used to mimic an anonymous class that is extending the
    // BroadcastCastReceiver abstract class, so this way we can implement its abstract functions here

    private val updateTime : BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent) {

            val action = intent.action

            if(StopWatchService.UPDATED_TIME == action) {

                time = intent.getDoubleExtra(StopWatchService.CURRENT_TIME, 0.0)
                getFormattedTime(time)
                binding.tvTime.text = time.toString()

            }
        }

    }

    private fun getFormattedTime(time: Double): String {

        val timeInt = time.roundToInt()
        val hours = timeInt % 86400 / 3600  // 86400 seconds in a day (in 24 hours), 3600 seconds in 1 hour
        val minutes = timeInt % 86400 % 3600 / 60
        val seconds = timeInt % 86400 % 3600 % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }


}