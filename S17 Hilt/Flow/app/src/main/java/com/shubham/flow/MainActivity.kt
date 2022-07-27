package com.shubham.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Flow is the replacement for RxJava and live data, it emits the value by filtering them in multiple ways.
        // Flow is not aware of the lifecycle so we need to convert its emitted data into LiveData


        // This is producer.
        /*val flow = flow<String> {

            for (i in 1..10) {
                emit("Hello World")
                delay(1000L)
            }
        }


        // Consumer
        GlobalScope.launch {
            flow.buffer().collect {
                Log.i("MY_TAG", "it: $it")
                delay(2000L)
            }
        }*/



        runBlocking {

            // using runBlocking cause .collect is a suspending function, and it only runs inside coroutine

            // this collect is a suspending function.
            getData().catch { e ->
                // you can handle exception if occurs here in this block
                Log.i("MYTAG", "Exception: ${e.message}")
            }.collect { data ->
                Log.i("MYTAG", "onCreate: $data")
                // we will collect the data asynchronously here on per 1 second, whenever getData() emits each value per second.
            }
        }

    }


    // fun is going to emit int type of flow
    private fun getData(): Flow<Int> = flow {
//                           this flow { } is the flow builder

        // operation/data inside flow is run on background thread.

        for(i in 1..5) {
            delay(1000)
            emit(i)
        }
    }.flowOn(Dispatchers.IO) // what this .flowOn() do is basically it sends the emitted data into background, we can pass IO,Main thread to it.

}