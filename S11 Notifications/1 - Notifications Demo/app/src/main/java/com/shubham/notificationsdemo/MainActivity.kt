package com.shubham.notificationsdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private val channelId = "com.shubham.notificationsdemo.channel1"
    private var notificationManager: NotificationManager? = null

    private val KEY_REPLY = "key_reply" // This key is used to receive users input

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        button = findViewById(R.id.button)

        createNotificationChannel(channelId, "Demo Channel", "This is a demo description.")

        button.setOnClickListener {
            displayNotification()
        }

        // 1] Intent 2] Create pending intent 3] create an action with the pending intent.



    }


    private fun displayNotification() {

        val notificationId = 45 // Use any value

        val tapResultIntent = Intent(this, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, tapResultIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT)


        // reply action
        val remoteInput: RemoteInput = RemoteInput.Builder(KEY_REPLY).run {
            setLabel("Insert your name here.")
                .build()
        }

        val replyAction : NotificationCompat.Action = NotificationCompat.Action.Builder(
            0, "REPLY", pendingIntent,
        ).addRemoteInput(remoteInput)
            .build()

        // Action Button 1

        val intent2 = Intent(this, DetailsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
        }

        val pendingIntent2 = PendingIntent.getActivity(this, 0, intent2,
            PendingIntent.FLAG_UPDATE_CURRENT)

        val action2: NotificationCompat.Action = NotificationCompat.Action.Builder(0,
                                                "Details", pendingIntent2).build()

        // Action Button 2

        val intent3 = Intent(this, SettingsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
        }

        val pendingIntent3 = PendingIntent.getActivity(this, 0, intent3,
            PendingIntent.FLAG_UPDATE_CURRENT)

        val action3: NotificationCompat.Action = NotificationCompat.Action.Builder(0,
            "Settings", pendingIntent3).build()

        val notification = NotificationCompat.Builder(this@MainActivity, channelId)
            .setContentTitle("Demo Title")
            .setContentText("This is a demo notification")
            .setSmallIcon(R.drawable.ic_baseline_electric_scooter_24)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .addAction(action2)
            .addAction(action3)
            .addAction(replyAction)
            .build()

        notificationManager?.notify(notificationId, notification)
    }


    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val importance = NotificationManager.IMPORTANCE_HIGH
            val channelInstance = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }
//            channelInstance.description = channelDescription

            notificationManager?.createNotificationChannel(channelInstance)
        }

    }

}