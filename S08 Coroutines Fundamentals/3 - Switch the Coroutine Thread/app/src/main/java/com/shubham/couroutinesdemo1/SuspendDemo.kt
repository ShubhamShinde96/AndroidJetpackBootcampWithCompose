package com.shubham.couroutinesdemo1

class SuspendDemo {

    private fun firstFunction() {

    }

    private suspend fun secondFunction() {

    }

//    *** From Instructor Notes ***

//    How Suspending Functions Work
//    A suspending function doesn't block a thread,
//
//    but only suspends the coroutine itself. (one thread can have more coroutines)
//
//    The thread is returned to the pool while the coroutine is waiting,
//
//    and when the waiting is done,
//
//    the coroutine resumes on a free thread in the pool.

}