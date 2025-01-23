package com.example.flowschannels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * flow builder generate a cold flow.
 * 1. It emits data only when there is a collector.
 * 2. It doesn't store the data
 * 3. It can't have multiple collector.
 */

private suspend fun producer() =  flow<Int> {
    repeat(5) {
        delay(1000)
        println("Emitted: $it")
        emit(it)
    }
}

fun coldFlow() = runBlocking {
    withContext(Dispatchers.IO) {
        val data : Flow<Int> = producer()
        println("Consumer Coroutine: Launched")
        val  job = CoroutineScope(Dispatchers.IO).async {
            //No collector - no emit
            data.collect { value ->
                println("Collected 1st: $value")
            }
            //A new fresh flow of data will start for each collector.
            data.collect { value ->
                println("Collected 2nd: $value")
            }
        }
        job.await()
        //delay(2500)
        //When job is cancelled, collector will unsubscribe the flow.
        // Then emition of data will stop from cold flow.
        //job.cancel()
    }
}


private fun main() {
    println("Calling: coldFlow()")
    coldFlow()
}

