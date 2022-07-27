package com.shubham.asyncwaitincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.shubham.asyncwaitincoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        binding.apply {



        }

//        CoroutineScope(Dispatchers.IO).launch {
        /*CoroutineScope(IO).launch {
        // To save time you can directly add IO instead of Dispatchers.IO

            Log.i("MyTag", "Calculation started...")
            val stock1 = getStock1()
            val stock2 = getStock2()
            val total = stock1 + stock2
            Log.i("MyTag", "Total is $total")

            // So this is a sequential decomposition & not a parallel decomposition
        }*/ // This operation was taking 18 seconds to complete, as this was getting executed sequentially
        // as task1 has 10 seconds wait time and task to has 8 seconds wait time


        // Now in above code the tasks are executing sequentially, to execute in parallel manner we need to use
        // Async Coroutine Builder
        // Now "launch" coroutine builder returns a job but AsyncCoroutineBuilder returns an instance of deferred
        // we can use that deferred value by invoking its await function.

        CoroutineScope(Main).launch {
            // To save time you can directly add IO instead of Dispatchers.IO

            Log.i("MyTag", "Calculation started...")
            val stock1 = async(IO) {getStock1()} // We can pass different type of dispatcher for async block
            val stock2 = async(IO) {getStock2()}

            // Now here, to get the return value of "getStock1()" and getStock2() function, we need to invoke
            // the await function of each await builder.

            val total = stock1.await() + stock2.await()
            Toast.makeText(applicationContext, "Total is $total", Toast.LENGTH_SHORT).show()
            Log.i("MyTag", "Total is $total")

            // So this is a sequential decomposition & not a parallel decomposition
        } // This operation is taking 10 seconds to execute, as we are using parallel executing using async & await


    }

    private suspend fun getStock1() : Int {
        delay(10000)
        Log.i("MyTag", "stock 1 returned")
        return 55000
    }

    private suspend fun getStock2() : Int {
        delay(8000)
        Log.i("MyTag", "stock 2 returned")
        return 35000
    }



}
























































