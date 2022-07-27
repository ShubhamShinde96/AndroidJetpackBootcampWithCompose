package com.shubham.couroutinesdemo1

import kotlinx.coroutines.*

class UserDataManager1 {

    suspend fun getTotalUserCount() : Int {

        var count = 0

        CoroutineScope(Dispatchers.IO).launch {

            delay(1000)
            count = 50
        }

        // This functions is returning 0 from here, cause what happening is before above coroutine executed the count is
        // returned, or you can say like because coroutine is like a java thread so code inside the thread will take as much
        // time as it wants but further code from the thread(coroutine) will keep executing parallel

        // This is the one weakness of "Unstructured Concurrency", it does not guarantee to complete all the tasks of the suspending
        // function, before it returns, the child coroutine can be still running even after the completion of the parent coroutine.
        // Here parent coroutine is the coroutine we are calling this class function from.

        // But if you use async builder and if you use await function call for the return value then you might be able to get the expected
        // result of the async coroutine.

        val deffered = CoroutineScope(Dispatchers.IO).async {

            delay(3000)
            count = 70
            return@async 70
        }

        // We should be getting 50 + 70 = 120 as a return value but we're getting only 70 as a return value from here.

        return count + deffered.await()
    }

}