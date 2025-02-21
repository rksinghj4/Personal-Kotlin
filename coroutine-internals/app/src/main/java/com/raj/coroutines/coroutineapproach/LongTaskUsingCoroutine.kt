package com.raj.coroutines.coroutineapproach

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val TAG = "LongTaskUsingCoroutine"

private fun callMeForLongTask() {
    println("Start callMeForLongTask")
    val exception = CoroutineExceptionHandler { _, _ ->
        println("Cached Exception in CoroutineExceptionHandler ")
    }
    /**
     * Why can' we pass Dispatchers.Main to GlobalScope.launch?
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
    callMeForLongTask()
    println("*****************************************")
    callMeForLongTaskConverted()
}

