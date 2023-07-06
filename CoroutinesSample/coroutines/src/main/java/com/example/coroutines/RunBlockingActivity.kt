package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

const val TAG = "RUN_BLOCKING"

class RunBlockingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var start = System.currentTimeMillis()
        Log.d(
            TAG,
            "Befor GlobalScope,  in Thread: " + Thread.currentThread().name + ", Start time : $start"
        )
        // Will not block the main thread
        GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
            val start = System.currentTimeMillis()
            delay(4000)
            val endTime = System.currentTimeMillis()
            Log.d(
                TAG,
                "CoroutineStart.DEFAULT, Dispatchers.Main, GlobalScope executes in Thread: "
                    + Thread.currentThread().name + ", Start time : $start, End time = $endTime"
            )
        }

        start = System.currentTimeMillis()
        Log.d(
            TAG,
            "Befor RunBlocking,  in Thread: " + Thread.currentThread().name + ", Start time : $start"
        )

        // runBlocking Will block the main thread untill it completes it's all job
        runBlocking {
            var start = System.currentTimeMillis()
            Log.d(
                TAG,
                "RunBlocking start in Thread: " + Thread.currentThread().name + ", Start time : $start"
            )

            //Note Coroutine 1 and Coroutine 2 execute parallel for 3 secs and will not block the main thread
            launch(Dispatchers.IO) {
                val start = System.currentTimeMillis()
                delay(3000L)
                val endTime = System.currentTimeMillis()
                Log.d(
                    TAG,
                    "In RunBlocking Coroutine 1, executes in Thread: "
                        + Thread.currentThread().name +
                        ", Start time : $start, End time = $endTime"
                )
            }

            launch(Dispatchers.IO) {
                val start = System.currentTimeMillis()
                delay(3000L)
                val endTime = System.currentTimeMillis()
                Log.d(
                    TAG,
                    "In RunBlocking Coroutine 2, executes in Thread: " + Thread.currentThread().name +
                        ", Start time : $start, End time = $endTime"
                )
            }
             start = System.currentTimeMillis()
            Log.d(
                TAG,
                "RunBlocking before 5 Secs delay,  Thread: " + Thread.currentThread().name
                    +"  Time = $start"
            )
            delay(5000L) // Block the main thread for 5 secs
            val endTime = System.currentTimeMillis()
            Log.d(
                TAG,
                "RunBlocking after 5 Secs delay, in Thread: " + Thread.currentThread().name +" End time = $endTime"
            )
        }
        /**
         * Once runBlocking complete it's all jobs it will release the main thread and then
         * control will come here.
         *
         */

        val endTime = System.currentTimeMillis()
        Log.d(
            TAG,
            "After RunBlocking end in Thread: " + Thread.currentThread().name +" End time = $endTime"
        )
    }
}