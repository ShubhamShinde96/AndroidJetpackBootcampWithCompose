package com.shubham.notificationsdemo

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat

class SecondActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    private val channelId = "com.shubham.notificationsdemo.channel1"
    val notificationId = 45

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textView = findViewById(R.id.textView3)

        receiveInput()

    }

    fun receiveInput() {

        val KEY_REPLY = "key_reply"

        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)

        if(remoteInput != null) {

            val inputString = remoteInput.getCharSequence(KEY_REPLY).toString()
            textView.text = inputString


            val repliedNotification = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_baseline_electric_scooter_24)
                .setContentText("Your reply received.")
                .build()

            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationId, repliedNotification)

        }
    }




}





































































