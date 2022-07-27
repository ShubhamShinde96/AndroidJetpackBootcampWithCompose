package com.shubham.hiltdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Hilt is a new android jetpack library to make dependency injection with dagger easier and efficient.
//        Hilt is like Room, like Room is a wrapper for sqlite, Since room generates most of the complex code part in the background, now wwe can easily implement db related functionalities very easily and efficiently.
//        Just like that, hilt is built on top of the dagger dependency injection library, providing a very easier and efficient way to implement dagger in our android applications.


        /*(application as App).dataComponent
            .inject(this)*/ // now we don't need to get an instance of component using the application

        // instead now we need to make this activity class as an entry point with @AndroidEntryPoint annotation.
        // see line no 8 above -> class MainActivity : AppCompatActivity() {
        // @AndroidEntryPoint generates an individual Hilt component for each android class marked with it.
        // These components are used to receive dependencies

        // Now rebuild the project.
        // So this is how we can use Hilt to implement dependency injection.

        dataSource.getRemoteData()

        // Now we're going to modify th App.kt class for Hilt


    }
}