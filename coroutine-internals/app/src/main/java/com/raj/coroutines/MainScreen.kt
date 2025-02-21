package com.raj.coroutines

import android.provider.Telephony.Threads
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

private var atomicNumExperimentOne = AtomicInteger(0)
private var atomicNumExperimentTwo = AtomicInteger(0)
private const val MAIN_SCREEN = "MAIN_SCREEN"

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            experimentOne()
        }) { Text(text = "Start Experiment One") }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            experimentTwo()
        }) { Text(text = "Start Experiment Two") }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            launchCoroutine()
        }) { Text(text = "Launch Coroutines") }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            launchThreads()
        }) { Text(text = "Launch Threads") }
    }
}

fun experimentOne() {
    atomicNumExperimentOne = AtomicInteger(0)
    val threadPool = Executors.newFixedThreadPool(40)

    //val startTime = SystemClock.elapsedRealtime()
    val startTime = System.currentTimeMillis()
    //Even thought more threads but More context switching - Slow performance
    repeat(10_000) {
        threadPool.execute {//Submitting 10_000 tasks to BlockingQueue<Runnable> so 10_000 threads needed
            longRunningTaskOne(startTime)
        }
    }
}

fun experimentTwo() {
    atomicNumExperimentTwo = AtomicInteger(0)
    val threadPool = Executors.newFixedThreadPool(4)

    //val startTime = SystemClock.elapsedRealtime()
    val startTime = System.currentTimeMillis()
    //Even thought less threads (1/10 of experimentOne) but less context switching - so fast performance
    repeat(4) {
        threadPool.execute {//Submitting 4 tasks to BlockingQueue<Runnable>, so only 4 threads needed
            repeat(2500) {//Each thread will execute 2500 longRunningTaskTwo
                longRunningTaskTwo(startTime)
            }
        }
    }
}

/**
 * Experiment1 time = 78
 */
fun longRunningTaskOne(startTime: Long) {
    if (atomicNumExperimentOne.incrementAndGet() == 10_000) {
        Log.d(MAIN_SCREEN, "Experiment1 time = ${System.currentTimeMillis() - startTime}")
    }
}

/**
 * Experiment2 time = 5
 */
fun longRunningTaskTwo(startTime: Long) {
    if (atomicNumExperimentTwo.incrementAndGet() == 10_000) {
        Log.d(MAIN_SCREEN, "Experiment2 time = ${System.currentTimeMillis() - startTime}")
    }
}

fun launchCoroutine() {
    repeat(100_000) {
        GlobalScope.launch { //Part of kotlin so default dispatcher is Dispatchers.Default
            delay(5000L)
        }
    }
}

/**
 * java.lang.OutOfMemoryError: pthread_create (1040KB stack) failed: Try again
 */
fun launchThreads() {
    repeat(100_000) {
        Thread {
            Thread.sleep(5000)
        }.start()
    }
}

