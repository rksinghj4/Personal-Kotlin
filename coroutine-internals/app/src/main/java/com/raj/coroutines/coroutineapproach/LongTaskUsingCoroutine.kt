package com.raj.coroutines.coroutineapproach

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 1. If you want a long-running thread, use Thread and make sure to call join()
 * to keep the main process alive while waiting for the background thread.
 * 2. If you want to use coroutines, launch inside runBlocking allows you
 * to run a background task that can live longer than the main thread still use job.join().
 * 3. If you're running an external process, you can use ProcessBuilder
 * to start a process that operates independently from your main program.
 *
 */
private val TAG = "LongTaskUsingCoroutine"

/**
 * Need to complete this. It's not running.
 */
private fun callMeForLongTaskMainFunWaitsToFinishLongerThirdApproach() {
    // Start an external process that runs independently
    val process = ProcessBuilder("Longer-than-main-process") //A long-running process
        .start()

    // Optionally, wait for the process to finish
    println("Main process continues while external process runs.")

    // You can use process.waitFor() if you want to wait for the external process to complete
    // process.waitFor() // Uncomment if you want the main thread to wait for the process to finish
}

private fun callMeForLongTaskMainFunWaitsToFinishLongerTaskSecondApproach() {
    println("Start callMeForLongTask")
    val exception = CoroutineExceptionHandler { _, _ ->
        println("Cached Exception in CoroutineExceptionHandler ")
    }
    //2. Thread Approach
    val thread = Thread{
        println("Start GlobalScope - callMeForLongTask")
        Thread.sleep(5000)
        println("Completed callMeForLongTask")
    }
    thread.start()
    thread.join()
    println("End callMeForLongTask")
}
//Using runBlocking and job.join()
private fun callMeForLongTaskMainFunWaitsToFinishLongerTaskFirstApproach() = runBlocking {
    println("Start callMeForLongTask")
    val exception = CoroutineExceptionHandler { _, _ ->
        println("Cached Exception in CoroutineExceptionHandler ")
    }

    /**
     * Q: Why can' we pass Dispatchers.Main to GlobalScope.launch?
     */
    //1. Coroutine Approach
    val job =
        CoroutineScope(exception + Dispatchers.IO).launch { //GlobalScope is part of Kotlin so By default: Dispatchers.Default
            println("Start GlobalScope - callMeForLongTask")
            val result = doTaskUsingCoroutine()
            println("Completed callMeForLongTask: $result")
        }
    job.join()
    println("End callMeForLongTask")
}

/**
 * Main doesn't wait for longer task to finish
 */
private fun callMeForLongTask() = runBlocking {
    println("Start callMeForLongTask")
    val exception = CoroutineExceptionHandler { _, _ ->
        println("Cached Exception in CoroutineExceptionHandler ")
    }

    /**
     * Q: Why can' we pass Dispatchers.Main to GlobalScope.launch?
     */
    CoroutineScope(exception + Dispatchers.IO).launch { //GlobalScope is part of Kotlin so By default: Dispatchers.Default
        println("Start GlobalScope - callMeForLongTask")
        val result = doTaskUsingCoroutine()
        println("Completed callMeForLongTask: $result")
    }
    println("End callMeForLongTask")
}

/**
 * Converted to following
 */

private fun callMeForLongTaskConverted() {
    println("Start callMeForLongTask")
    val exception = CoroutineExceptionHandler { _, _ ->
        println("Cached Exception in CoroutineExceptionHandler ")
    }
    CoroutineScope(exception + Dispatchers.IO).launch { //GlobalScope is part of Kotlin so By default: Dispatchers.Default
        println("Start GlobalScope - callMeForLongTask")
        doTaskUsingCoroutine(object : Continuation {
            override fun resumeWith(result: String) {
                println("Completed callMeForLongTask: $result")
            }
        })
    }
    println("End callMeForLongTask")
}


private suspend fun doTaskUsingCoroutine(): String {
    println("start doTaskUsingCoroutine")
    delay(5000L)
    println("end doTaskUsingCoroutine")
    return "Long task is result"
}

/**
 * under the hood doTaskUsingCoroutine() converted to following
 * 1. remove suspend, and return type
 * 2. pass continuation: Continuation listener where resumeWith(same type as return type of suspend fun)
 */

private fun doTaskUsingCoroutine(continuation: Continuation) {
    println("start doTaskUsingCoroutine")
    Thread {
        Thread.sleep(5000)
        continuation.resumeWith("Long task is result")
    }
    println("end doTaskUsingCoroutine")
}

interface Continuation {
    fun resumeWith(result: String)
}

private fun main() {
    //main waits for longer task to finish
    //callMeForLongTaskMainFunWaitsToFinishLongerTaskFirstApproach()
    //main waits for longer task to finish
    callMeForLongTaskMainFunWaitsToFinishLongerTaskSecondApproach()
    //Not working - need to check
    //callMeForLongTaskMainFunWaitsToFinishLongerThirdApproach()
    //main doesn't waits for longer task to finish
    //callMeForLongTask()
    println("*****************************************")
    callMeForLongTaskConverted()
}

