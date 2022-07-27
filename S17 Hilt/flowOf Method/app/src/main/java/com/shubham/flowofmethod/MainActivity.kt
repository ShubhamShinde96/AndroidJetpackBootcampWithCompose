package com.shubham.flowofmethod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // basically flowOf() storing data,    to send data on background we have used flowOn(ThreadType)
        val data = flowOf(1,2,3,4,5).flowOn(Dispatchers.IO)

        runBlocking {

            data.collect {
                Log.i("MYTAG", "onCreate: $it")
            }
        }


        // *************************************************************

        // new section : asFlow() function

        val data2 = listOf(1,2,3,4,5,6).asFlow().flowOn(Dispatchers.IO) // This is how we can convert this list into flow

        runBlocking {

            data2.collect {
                Log.i("MYTAG", "data2: $it")
            }
        }


        // *************************************************************

        // new section : map operator it flow


        val data3 = flowOf(1,2,3,4,5).flowOn(Dispatchers.IO)

        runBlocking {

            data3.map { value ->

                value * value // This is a example, you can perform any operation like this here.

            }.collect {
                Log.i("MYTAG", "MAP: $it")
            }
        }

        // *************************************************************

        // new section : filter operator in flow api

        val data4 = flowOf(1,2,3,4,5,6,7,8,9,10).flowOn(Dispatchers.IO)

        runBlocking {

            data4.filter { value ->

                value%2 == 0 // We basically filtering only even values, and leaving odd values

            }.collect {
                Log.i("MYTAG", "FILTER: $it")
            }
        }

    }
}