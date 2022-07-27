package com.shubham.couroutinesdemo1

import kotlinx.coroutines.*

class UserDataManager2 {

    var count = 0
    lateinit var deferred: Deferred<Int>

    suspend fun getTotalUserCount(): Int {

        // inside this suspending function we are using "coroutineScope" suspending function to provide a child scope
        coroutineScope {

            // IMP: so this suspending function "coroutineScope" waits(make sure) for the child coroutines to complete execution

            // now we can use launch and async builders inside this child scope

            // if we do not add any dispatcher to the launch function then it will run on the dispatcher of parent scope.
            // we are calling this function from main activity on Main thread so it will run on that if we do not specify here.
            launch(Dispatchers.IO) {
                delay(1000)
                count = 50
            }

            deferred = async(Dispatchers.IO) {

                delay(3000)
                return@async 70
            }


        }

        // during previous lesson with unstructured concurrency we was getting only 70 instead now we're getting correct value
        // 50 + 70 = 120

        return count + deferred.await()
    }

}